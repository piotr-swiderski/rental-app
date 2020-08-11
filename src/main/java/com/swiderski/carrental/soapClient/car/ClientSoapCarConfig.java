package com.swiderski.carrental.soapClient.car;

import com.swiderski.rental_service.schema.car.CarSOAP;
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
    public CarSOAP carServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(CarSOAP.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        return (CarSOAP) jaxWsProxyFactoryBean.create();
    }

    @Bean("CarObjectFactory")
    public ObjectFactory carObjectFactory() {
        return new ObjectFactory();
    }
}

