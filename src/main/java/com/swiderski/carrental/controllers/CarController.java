package com.swiderski.carrental.controllers;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.mapper.CarMapper;
import com.swiderski.carrental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("car-rental-api/car")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @Autowired
    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping(value = "/all")
    public Set<CarDto> getAllCars() {
        Set<Car> allCars = carService.getAllCars();
        return carMapper.carSetToCarDtoSet(allCars);
    }

    @GetMapping(value = "/{id}")
    public CarDto getCarById(@PathVariable long id) {
        Car carById = carService.getCarById(id);
        return carMapper.carToCarDto(carById);
    }

    @PostMapping(value = "/add")
    public CarDto addCar(@RequestBody CarDto carDto) {
        Car car = carMapper.carDtoToCar(carDto);
        carService.saveCar(car);
        return carDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    public CarDto deleteCar(@PathVariable long id) {
        Car car = carService.deleteCarById(id);
        return carMapper.carToCarDto(car);
    }

    @PutMapping("/update/{id}")
    public CarDto updateCar(@PathVariable long id, @RequestBody CarDto carDto) {
        Car car = carMapper.carDtoToCar(carDto);
        Car carUpdated = carService.updateCar(id, car);
        return carMapper.carToCarDto(carUpdated);
    }

}
