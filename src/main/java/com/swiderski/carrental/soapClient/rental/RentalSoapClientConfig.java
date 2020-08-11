package com.swiderski.carrental.soapClient.rental;

import com.swiderski.rental_service.schema.rental.ObjectFactory;
import com.swiderski.rental_service.schema.rental.RentalSOAP;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentalSoapClientConfig {


    @Value("${client.soap.rental.address}")
    private String address;

    @Bean(name = "rentalProxy")
    public RentalSOAP rentalServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(RentalSOAP.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        return (RentalSOAP) jaxWsProxyFactoryBean.create();
    }

    @Bean("rentalObjectFactory")
    public ObjectFactory clientObjectFactory() {
        return new ObjectFactory();
    }
}

