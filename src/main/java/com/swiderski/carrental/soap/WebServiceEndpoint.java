package com.swiderski.carrental.soap;

import com.javainuse.InputSOATest;
import com.javainuse.OutputSOATest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WebServiceEndpoint {

    private static final String NAMESPACE_URI = "http://javainuse.com";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputSOATest")
    @ResponsePayload
    public OutputSOATest hello(@RequestPayload InputSOATest request) {

        OutputSOATest outputSOATest = new OutputSOATest();
        String test = request.getTest();
        outputSOATest.setResult("HELLO " + test);
        return outputSOATest;
    }

}