package com.swiderski.carrental.feignClient;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FeignExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(FeignException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String feignUnauthorizedException(feign.FeignException e) {
        return "Unauthorized";
    }

    @ResponseBody
    @ExceptionHandler(feign.FeignException.Forbidden.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String feignForbiddenException(feign.FeignException e) {
        return "Forbidden";
    }

}
