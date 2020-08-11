package com.swiderski.carrental.soapClient.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractSoapClientController<E extends AbstractDto, T extends CommonSoapClient<E>> {

    T clientServiceProxy;

    public AbstractSoapClientController(T clientServiceProxy) {
        this.clientServiceProxy = clientServiceProxy;
    }

    @GetMapping("/{id}")
    public E getById(@PathVariable long id) {
        return clientServiceProxy.getById(id);
    }

    @PostMapping
    public E add(@RequestBody E dto) {
        return clientServiceProxy.add(dto);
    }

    @DeleteMapping("/{id}")
    public E delete(@PathVariable long id) {
        return clientServiceProxy.delete(id);
    }

    @PutMapping("/{id}")
    public E update(@RequestBody E dto, @PathVariable long id) {
        return clientServiceProxy.update(dto, id);
    }

}
