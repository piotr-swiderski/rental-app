package com.swiderski.carrental.soapClient.client;

import com.swiderski.rental_service.schema.client.ClientClient;
import com.swiderski.rental_service.schema.client.ObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ClientSoapClientConfig {

    @Value("${client.soap.client.address}")
    private String address;

    @Bean(name = "clientProxy")
    public ClientClient clientServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(ClientClient.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        return (ClientClient) jaxWsProxyFactoryBean.create();
    }

    @Bean("ClientObjectFactory")
    public ObjectFactory clientObjectFactory() {
        return new ObjectFactory();
    }
}

