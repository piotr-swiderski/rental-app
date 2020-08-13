package com.swiderski.carrental.feignClient.products;

import com.swiderski.carrental.feignClient.CustomPageImpl;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public class ProductClientFallback implements ProductClient {


    @Override
    public CustomPageImpl<ProductDTO> findAll(Pageable page, String search) {
        return null;
    }

    @Override
    public ProductDTO findById(Long id) {
        return new ProductDTO();
    }

    @Override
    public ProductDTO save(@Valid ProductDTO dto) {
        return new ProductDTO();
    }

    @Override
    public ProductDTO update(Long id, @Valid ProductDTO dto) {
        return new ProductDTO();
    }

    @Override
    public ProductDTO delete(Long id) {
        return new ProductDTO();
    }
}
