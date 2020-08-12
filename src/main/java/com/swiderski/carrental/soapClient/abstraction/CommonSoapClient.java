package com.swiderski.carrental.soapClient.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;

public interface CommonSoapClient<E extends AbstractDto> {

    E delete(long id);

    E getById(long id);

    E add(E dto);

    E update(E dto, long id);
}
