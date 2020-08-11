package com.swiderski.carrental.feignClient.products;

import com.swiderski.carrental.feignClient.OAuth2RequestInterceptorConfiguration;
import com.swiderski.carrental.feignClient.abstraction.CommonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProductClient", url = "s0291:8081/api/products", configuration = OAuth2RequestInterceptorConfiguration.class)
public interface ProductClient extends CommonClient<ProductDTO> {

}
