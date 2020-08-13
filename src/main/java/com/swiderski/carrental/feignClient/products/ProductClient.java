package com.swiderski.carrental.feignClient.products;

import com.swiderski.carrental.feignClient.TokenAccessInterceptor;
import com.swiderski.carrental.feignClient.abstraction.CommonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProductClient",
        url = "s0291:8087/api/products",
        configuration = TokenAccessInterceptor.class,
        fallback = ProductClientFallback.class)
public interface ProductClient extends CommonClient<ProductDTO> {

}
