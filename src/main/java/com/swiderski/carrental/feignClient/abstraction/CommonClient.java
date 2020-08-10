package com.swiderski.carrental.feignClient.abstraction;


import com.swiderski.carrental.feignClient.CustomPageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface CommonClient<E extends AbstractDto> {

    @GetMapping
    CustomPageImpl<E> findAll(@PageableDefault Pageable page,
                              @RequestParam(value = "search", required = false) String search,
                              @RequestHeader("Authorization") String authHeader);

    @GetMapping(path = "/{id}")
    E findById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader);

    @PostMapping
    E save(@Valid @RequestBody E dto, @RequestHeader("Authorization") String authHeader);

    @PutMapping(path = "/{id}")
    E update(@PathVariable("id") Long id, @Valid @RequestBody E dto, @RequestHeader("Authorization") String authHeader);

    @DeleteMapping(path = "/{id}")
    E delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader);
}
