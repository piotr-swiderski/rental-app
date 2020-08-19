package com.swiderski.carrental.soapClient.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.abstraction.CommonParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonSoapClient<E extends AbstractDto, T extends CommonParam> {

    E delete(long id);

    E getById(long id);

    E add(E dto);

    E update(E dto, long id);

    Page<E> getAll(T param, Pageable pageable);
}
