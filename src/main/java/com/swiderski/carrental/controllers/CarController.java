package com.swiderski.carrental.controllers;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car-rental-api/car")
public class CarController extends AbstractController<CarService, CarDto> {

    @Autowired
    public CarController(CarService carService) {
        super(carService);
    }
}
