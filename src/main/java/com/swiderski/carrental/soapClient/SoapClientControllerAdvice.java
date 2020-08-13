package com.swiderski.carrental.soapClient;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebServiceException;

@ControllerAdvice
public class SoapClientControllerAdvice {

    @ResponseBody
    @ExceptionHandler(SoapFault.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(SoapFault e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WebServiceException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public String webServiceExceptionHandler(WebServiceException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Fault.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public String FaultHandler(Fault e) {
        return e.getMessage();
    }
}
