package com.swiderski.carrental.soapClient.car;

import com.swiderski.rental_service.schema.car.CarClient;
import com.swiderski.rental_service.schema.car.ObjectFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ClientSoapCarConfig {

    @Value("${client.soap.car.address}")
    private String address;

    @Bean(name = "carProxy")
    public CarClient carServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CarClient.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        return (CarClient) jaxWsProxyFactoryBean.create();
    }

    @Bean("CarObjectFactory")
    public ObjectFactory carObjectFactory() {
        return new ObjectFactory();
    }
}

