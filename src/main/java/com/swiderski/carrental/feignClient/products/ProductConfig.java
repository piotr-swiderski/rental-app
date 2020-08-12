package com.swiderski.carrental.feignClient.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductClientFallback productClientFallback() {
        return new ProductClientFallback();
    }
}
