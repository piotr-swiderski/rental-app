package com.swiderski.carrental.crud.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id, String className) {
        super("Could not find " + className + " with id = " + id);
    }
}
