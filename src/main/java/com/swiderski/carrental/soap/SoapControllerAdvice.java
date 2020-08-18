package com.swiderski.carrental.soap;

import org.apache.cxf.interceptor.Fault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SoapControllerAdvice {


    @ResponseBody
    @ExceptionHandler(Fault.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String feignUnauthorizedException(Fault e) {
        return e.getMessage();
    }

}
