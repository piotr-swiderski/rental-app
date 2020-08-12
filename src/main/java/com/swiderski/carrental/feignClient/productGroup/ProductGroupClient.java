package com.swiderski.carrental.feignClient.productGroup;

import com.swiderski.carrental.feignClient.TokenAccessInterceptor;
import com.swiderski.carrental.feignClient.abstraction.CommonClient;
import org.springframework.cloud.openfeign.FeignClient;

//81
@FeignClient(name = "ProductGroupClient",
        url = "s0291:8087/api/groups",
        configuration = TokenAccessInterceptor.class,
        fallback = ProductGroupClientFallback.class)
public interface ProductGroupClient extends CommonClient<ProductGroupDTO> {
}
