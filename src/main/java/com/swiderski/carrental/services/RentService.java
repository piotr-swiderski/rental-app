package com.swiderski.carrental.services;

import com.swiderski.carrental.entity.Car;

import java.util.Set;

public interface RentService {

    //todo rentCar, returnCar, getAvailableCars, getRentalCars

    Boolean rentCar(long id);

    Boolean returnCar(long id);

    Set<Car> getAvailableCars();

    Set<Car> getRentalCars();
}
