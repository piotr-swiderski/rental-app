package com.swiderski.carrental.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface CommonService<D> {

    D save(D dto);

    D getById(long id);

    Page<D> getAll(Specification specification, int pageNo, int pageSize, String sortBy);

    D update(long id, D dto);

    D delete(long id);
}
