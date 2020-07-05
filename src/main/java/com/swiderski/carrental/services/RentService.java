package com.swiderski.carrental.services;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Rental;

import java.time.LocalDate;
import java.util.Set;

public interface RentService {

    Rental getRentalById(long id);

    Rental rentCar(long carId, long clientId);

    Rental returnCar(long id, LocalDate returnDate);

    Set<Car> getAvailableCars();

    Set<Car> getRentedCars();

    Set<Rental> getAllRentals();
}
