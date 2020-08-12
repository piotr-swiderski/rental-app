package com.swiderski.carrental.soapClient;

import org.apache.cxf.binding.soap.SoapFault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SoapClientControllerAdvice {

    @ResponseBody
    @ExceptionHandler(SoapFault.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(SoapFault e) {
        return e.getMessage();
    }
}
