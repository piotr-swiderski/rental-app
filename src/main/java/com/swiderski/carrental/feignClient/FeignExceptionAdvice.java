package com.swiderski.carrental.feignClient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FeignExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(feign.FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String feignException(feign.FeignException e) {
        return e.getMessage();
    }

}
