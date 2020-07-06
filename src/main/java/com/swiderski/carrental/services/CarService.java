package com.swiderski.carrental.services;

import com.swiderski.carrental.entity.Car;

import java.util.Optional;
import java.util.Set;

public interface CarService {

    Car saveCar(Car car);

    Car deleteCarById(long id);

    Car updateCar(long id, Car car);

    Car getCarById(long id);

    Set<Car> getAllCars();

}
