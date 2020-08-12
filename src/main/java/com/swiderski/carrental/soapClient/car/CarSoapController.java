package com.swiderski.carrental.soapClient.car;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
}
