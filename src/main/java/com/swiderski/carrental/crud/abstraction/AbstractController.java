package com.swiderski.carrental.crud.abstraction;


import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}"})
public abstract class AbstractController<T extends CommonService<E>, E extends AbstractDto> {

    private final T service;

    public AbstractController(T service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('create_profile')")
    public E getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('read_profile')")
    public E save(@RequestBody E dto) {
        return service.save(dto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('delete_profile')")
    public E delete(@PathVariable long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('update_profile')")
    public E update(@PathVariable long id, @RequestBody E dto) {
        return service.update(id, dto);
    }

}
