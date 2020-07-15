package com.swiderski.carrental.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
public abstract class AbstractController<T extends CommonService<E>, E extends AbstractDto> {

    private final T service;

    public AbstractController(T service) {
        this.service = service;
    }

    @GetMapping
    public Page<E> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "id") String sortBy) {
        return service.getAll(pageNo, pageSize, sortBy);
    }

    @GetMapping(value = "/{id}")
    public E getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public E save(@RequestBody E dto) {
        return service.save(dto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public E delete(@PathVariable long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public E update(@PathVariable long id, @RequestBody E dto) {
        return service.update(id, dto);
    }

}
