package com.swiderski.carrental.feignClient.productGroup;

import com.swiderski.carrental.feignClient.abstraction.AbstractDto;
import com.swiderski.carrental.feignClient.products.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductGroupDTO extends AbstractDto {

    private String name;
    private String description;
    private Set<ProductDTO> products;
}
