package com.swiderski.carrental.crud.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.ConstraintViolationException;

import static com.swiderski.carrental.crud.car.CarMessageUtils.MIN_YEAR_VALID_MESSAGE;
import static com.swiderski.carrental.crud.exception.Utils.BAD_REQUEST_MESSAGE;
import static com.swiderski.carrental.crud.exception.Utils.INTEGRITY_EXCEPTION_MESSAGE;

@ControllerAdvice
@Validated
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CarRentedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String carRentedExceptionHandler(CarRentedException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String dataIntegrityViolationExceptionHandler() {
        return INTEGRITY_EXCEPTION_MESSAGE;
    }

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequestExceptionHandler(RuntimeException e) {
        return BAD_REQUEST_MESSAGE;
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceAccessException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String resourceExceptionHandler(ResourceAccessException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String constraintViolationException(ConstraintViolationException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String bindExceptionHandler(BindException e) {
        return MIN_YEAR_VALID_MESSAGE;
    }

    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bindExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return e.getMessage();
    }

}
