package com.swiderski.carrental.soapClient.rental;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import com.swiderski.carrental.xlsxGenerator.XlsxGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.swiderski.carrental.soapClient.MessageUtils.ID_VALID_MESSAGE;

@RestController
@RequestMapping("v1/soap/rentals")
public class RentalSoapController extends AbstractSoapClientController<RentalDto, RentalSoapClient> {


    private final RentalSoapClient rentalClientProxy;

    public RentalSoapController(RentalSoapClient rentalClientProxy) {
        super(rentalClientProxy);
        this.rentalClientProxy = rentalClientProxy;
    }

    @GetMapping
    @ApiPageable
    public Page<RentalDto> getAll(@ModelAttribute RentalParam rentalParam,
                                  @ApiIgnore @NotNull Pageable pageable) {
        return rentalClientProxy.getAll(rentalParam, pageable);
    }

    @PostMapping("/return")
    public RentalDto returnCar(@PositiveOrZero(message = ID_VALID_MESSAGE) long rentalId,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate rentalEnd) {
        return rentalClientProxy.returnCar(rentalId, rentalEnd);
    }

    @PostMapping("/rent")
    public RentalDto rentCar(@PositiveOrZero(message = ID_VALID_MESSAGE) long carId,
                             @PositiveOrZero(message = ID_VALID_MESSAGE) long clientId) {
        return rentalClientProxy.rentCar(carId, clientId);
    }

    @GetMapping(value = "/excel", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getPdf() {
        Page<RentalDto> all = rentalClientProxy.getAll(new RentalParam(), PageRequest.of(0,20, Sort.by("id")));
        byte[] excel = XlsxGenerator.customersToExcel(all.getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".xlsx\"")
                .body(excel);
    }
}
