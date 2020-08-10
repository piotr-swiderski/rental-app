package com.swiderski.carrental.feignClient.productGroup;

import com.swiderski.carrental.feignClient.abstraction.CommonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProductGroupClient", url = "s0291:8081/api/groups")
public interface ProductGroupClient extends CommonClient<ProductGroupDTO> {
}
