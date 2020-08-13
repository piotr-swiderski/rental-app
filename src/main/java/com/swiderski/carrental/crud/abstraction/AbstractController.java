package com.swiderski.carrental.crud.abstraction;


import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import static com.swiderski.carrental.crud.abstraction.MessageUtils.ID_POSITIVE_MESSAGE;

@CrossOrigin
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}"})
@Validated
public abstract class AbstractController<T extends CommonService<E>, E extends AbstractDto> {

    private final T service;

    public AbstractController(T service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('create_profile')")
    @Validated
    public E getById(@Valid @PositiveOrZero(message = ID_POSITIVE_MESSAGE) @PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('read_profile')")
    public E save(@RequestBody @Valid E dto) {
        return service.save(dto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('delete_profile')")
    public E delete(@PathVariable @Validated @PositiveOrZero(message = ID_POSITIVE_MESSAGE) long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('update_profile')")
    public E update(@PathVariable @Validated @PositiveOrZero(message = ID_POSITIVE_MESSAGE) long id,
                    @RequestBody @Valid E dto) {
        return service.update(id, dto);
    }

}
