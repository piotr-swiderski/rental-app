package com.swiderski.carrental.feignClient.productGroup;

import com.swiderski.carrental.feignClient.CustomPageImpl;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public class ProductGroupClientFallback implements ProductGroupClient {
    @Override
    public CustomPageImpl<ProductGroupDTO> findAll(Pageable page, String search) {
        return new CustomPageImpl<>(List.of(new ProductGroupDTO()), page, 0);

    }

    @Override
    public ProductGroupDTO findById(Long id) {
        return new ProductGroupDTO();
    }

    @Override
    public ProductGroupDTO save(@Valid ProductGroupDTO dto) {
        return new ProductGroupDTO();
    }

    @Override
    public ProductGroupDTO update(Long id, @Valid ProductGroupDTO dto) {
        return new ProductGroupDTO();
    }

    @Override
    public ProductGroupDTO delete(Long id) {
        return new ProductGroupDTO();
    }
}
