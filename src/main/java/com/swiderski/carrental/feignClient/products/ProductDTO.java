package com.swiderski.carrental.feignClient.products;

import com.swiderski.carrental.feignClient.abstraction.AbstractDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends AbstractDto {

    private String name;
    private String description;
    private int price;
    private boolean sold;
    private Long groupId;

}
