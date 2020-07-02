package com.swiderski.carrental.services;

import com.swiderski.carrental.entity.Car;

import java.util.Optional;

public interface CarService {

    Car saveCar(Car car);

    Car deleteCarById(long id);

    Car updateCar(long id, Car car);

    Car getCarById(long id);


}
