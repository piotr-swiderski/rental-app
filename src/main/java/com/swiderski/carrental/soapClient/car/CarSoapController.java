package com.swiderski.carrental.soapClient.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/soap/cars")
public class CarSoapController extends AbstractSoapClientController<CarDto, CarSoapClient, CarParam> {

    public CarSoapController(CarSoapClient clientServiceProxy) {
        super(clientServiceProxy);
    }
}
