package com.swiderski.carrental.soapClient.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import static com.swiderski.carrental.soapClient.MessageUtils.ID_VALID_MESSAGE;

@Validated
public abstract class AbstractSoapClientController<E extends AbstractDto, T extends CommonSoapClient<E>> {

    T clientServiceProxy;

    public AbstractSoapClientController(T clientServiceProxy) {
        this.clientServiceProxy = clientServiceProxy;
    }

    @GetMapping("/{id}")
    public E getById(@PositiveOrZero(message = ID_VALID_MESSAGE) @PathVariable long id) {
        return clientServiceProxy.getById(id);
    }

    @PostMapping
    public E add(@Valid @RequestBody E dto) {
        return clientServiceProxy.add(dto);
    }

    @DeleteMapping("/{id}")
    public E delete(@PositiveOrZero(message = ID_VALID_MESSAGE) @PathVariable long id) {
        return clientServiceProxy.delete(id);
    }

    @PutMapping("/{id}")
    public E update(@Valid @RequestBody E dto,
                    @PositiveOrZero(message = ID_VALID_MESSAGE) @PathVariable long id) {
        return clientServiceProxy.update(dto, id);
    }

}
