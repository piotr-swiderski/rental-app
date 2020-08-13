package com.swiderski.carrental.soapClient.client;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.xml.ws.WebServiceException;

@Aspect
@Component
@Slf4j
public class ClientAspect {

    @AfterThrowing(pointcut = "execution(* com.swiderski.carrental.soapClient.client.ClientSoapClient.*(..)))", throwing = "webServiceException")
    public void connectionFailedLog(JoinPoint joinPoint, WebServiceException webServiceException) {
        joinPoint.getSourceLocation();
        log.info("Connection failed to web service");
    }

}
