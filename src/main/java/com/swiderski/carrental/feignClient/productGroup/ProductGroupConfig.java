package com.swiderski.carrental.feignClient.productGroup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductGroupConfig {

    @Bean
    ProductGroupClientFallback productGroupClientFallback() {
        return new ProductGroupClientFallback();
    }
}
