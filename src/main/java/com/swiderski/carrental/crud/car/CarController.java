package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car-rental-api/car")
public class CarController extends AbstractController<CarService, CarDto> {

    @Autowired
    public CarController(CarService service) {
        super(service);
    }
}
