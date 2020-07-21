package com.swiderski.carrental.crud.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(NotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CarRentedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String carRentedException(CarRentedException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String dataIntegrityViolationExceptionHandler() {
        return "Integrity exception";
    }

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequestHandler(RuntimeException e) {
        return "bad request";
    }
}
