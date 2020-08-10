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

public class AbstractController<E extends AbstractDto, T extends CommonClient<E>> {

    private final T client;

    public AbstractController(T client) {
        this.client = client;
    }

    @GetMapping
    public CustomPageImpl<E> findAll(@PageableDefault Pageable page,
                                     @RequestParam(value = "search", required = false) String search,
                                     @RequestHeader String authHeader) {
        return client.findAll(page, search, authHeader);
    }

    @GetMapping(path = "/{id}")
    public E findById(@PathVariable("id") Long id, @RequestHeader String authHeader) {
        return client.findById(id, authHeader);
    }

    @PostMapping
    public E save(@Valid @RequestBody E dto, @RequestHeader String authHeader) {
        return client.save(dto, authHeader);
    }

    @PutMapping(path = "/{id}")
    public E update(@PathVariable("id") Long id, @Valid @RequestBody E dto, @RequestHeader String authHeader) {
        return client.update(id, dto, authHeader);
    }

    @DeleteMapping(path = "/{id}")
    public E delete(@PathVariable("id") Long id, @RequestHeader String authHeader) {
        return client.delete(id, authHeader);
    }

}
