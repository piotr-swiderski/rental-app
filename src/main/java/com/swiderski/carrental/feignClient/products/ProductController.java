package com.swiderski.carrental.feignClient.products;

import com.swiderski.carrental.feignClient.abstraction.AbstractController;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/products")
@EnableFeignClients(basePackageClasses = ProductClient.class)
public class ProductController extends AbstractController<ProductDTO, ProductClient> {

    public ProductController(ProductClient client) {
        super(client);
    }
}
