package com.swiderski.carrental.soapClient.car;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import com.swiderski.carrental.xlsxGenerator.XlsxGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/soap/cars")
public class CarSoapController extends AbstractSoapClientController<CarDto, CarSoapClient> {

    private final CarSoapClient carClientProxy;

    public CarSoapController(CarSoapClient clientServiceProxy) {
        super(clientServiceProxy);
        this.carClientProxy = clientServiceProxy;
    }

    @GetMapping
    @ApiPageable
    public Page<CarDto> getAll(@ModelAttribute CarParam carParam,
                               @ApiIgnore @NotNull Pageable pageable) {
        return carClientProxy.getAll(carParam, pageable);
    }

    @GetMapping(value = "/excel", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getExcel() {
        Page<CarDto> all = carClientProxy.getAll(new CarParam(), PageRequest.of(0,20, Sort.by("id")));
        byte[] excel = XlsxGenerator.customersToExcel(all.getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".xlsx\"")
                .body(excel);
    }
}
