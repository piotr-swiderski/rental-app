package com.swiderski.carrental.feignClient.abstraction;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.feignClient.utils.CustomPageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static com.swiderski.carrental.feignClient.utils.MessageUtils.ID_VALID_MESSAGE;

@Validated
public class AbstractController<E extends AbstractDto, T extends CommonClient<E>> {

    private final T client;

    public AbstractController(T client) {
        this.client = client;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @ApiPageable
    public CustomPageImpl<E> findAll(@ApiIgnore @NonNull Pageable page,
                                     @RequestParam(value = "search", required = false) String search) {
        return client.findAll(page, search);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public E findById(@Positive(message = ID_VALID_MESSAGE) @PathVariable("id") Long id) {
        return client.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public E save(@Valid @RequestBody E dto) {
        return client.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public E update(@PathVariable("id") Long id,
                    @Valid @RequestBody E dto) {
        return client.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public E delete(@Positive(message = ID_VALID_MESSAGE) @PathVariable("id") Long id) {
        return client.delete(id);
    }

}
