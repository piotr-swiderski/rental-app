package com.swiderski.carrental.soap;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

import javax.annotation.PostConstruct;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    private static final String BINDING_URI = "http://www.w3.org/2003/05/soap/bindings/HTTP/";

    @Autowired
    private List<SoapService> endpoints;


    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);

        return new ServletRegistrationBean(servlet, "/ws/*");
    }


    @Bean(name = "carSchema")
    public SimpleWsdl11Definition carWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/car.wsdl"));
        return wsdl11Definition;
    }

    @Bean(name = "clientSchema")
    public SimpleWsdl11Definition clientWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/client.wsdl"));
        return wsdl11Definition;
    }

    @Bean(name = "rentalSchema")
    public SimpleWsdl11Definition rentalWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/rental.wsdl"));
        return wsdl11Definition;
    }

    @Bean
    public ServletRegistrationBean<CXFServlet> disServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus bus = new SpringBus();
        bus.setProperty("org.apache.cxf.stax.maxTextLength", 1024 * 1024 * 1024);
        return bus;
    }

    @PostConstruct
    public void init() {
        for (SoapService bean : endpoints) {
            if (bean.getClass().getAnnotation(SoapEndpoint.class) == null) {
                throw new IllegalArgumentException("Missed @SoapEndpoint for " + bean.getClass().getName());
            }
            EndpointImpl endpoint = new EndpointImpl(springBus(), bean);
            endpoint.setBindingUri(BINDING_URI);
            endpoint.publish(bean.getClass().getAnnotation(SoapEndpoint.class).publish());
        }
    }
}
