package com.swiderski.carrental.feignClient.productGroup;

import com.swiderski.carrental.feignClient.abstraction.AbstractController;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/groups")
@EnableFeignClients(basePackageClasses = ProductGroupClient.class)
public class ProductGroupController extends AbstractController<ProductGroupDTO, ProductGroupClient> {

    public ProductGroupController(ProductGroupClient client) {
        super(client);
    }
}
