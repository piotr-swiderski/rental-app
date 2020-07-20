package com.swiderski.carrental.crud.abstraction;

import org.springframework.data.domain.Page;

public interface CommonService<D> {

    D save(D dto);

    D getById(long id);

    Page<D> getAll(int pageNo, int pageSize, String sortBy);

    D update(long id, D dto);

    D delete(long id);
}
