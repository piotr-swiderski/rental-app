package com.swiderski.carrental.crud.exception;

public class CarRentedException extends RuntimeException {

    public CarRentedException(long id) {
        super("Car with id = " + id + " is rented");
    }
}
