package com.swiderski.carrental.soapClient.rental;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("v1/soap/rentals")
public class RentalSoapController extends AbstractSoapClientController<RentalDto, RentalSoapClient> {

    private final RentalSoapClient rentalClientProxy;

    public RentalSoapController(RentalSoapClient rentalClientProxy) {
        super(rentalClientProxy);
        this.rentalClientProxy = rentalClientProxy;
    }

    @GetMapping
    public Page<RentalDto> getAll(@ModelAttribute RentalParam rentalParam,
                                  @PageableDefault Pageable pageable) {
        return rentalClientProxy.getAll(rentalParam, pageable);
    }

    @PostMapping("/return")
    public RentalDto returnCar(long rentalId, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate rentalEnd) {
        return rentalClientProxy.returnCar(rentalId, rentalEnd);
    }

    @PostMapping("/rent")
    public RentalDto rentCar(long carId, long clientId) {
        return rentalClientProxy.rentCar(carId, clientId);
    }
}
