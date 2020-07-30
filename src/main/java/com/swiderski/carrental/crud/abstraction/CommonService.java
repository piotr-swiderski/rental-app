package com.swiderski.carrental.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CommonService<D> {

    D save(D dto);

    D getById(long id);

    D update(long id, D dto);

    D delete(long id);
}
