package com.swiderski.carrental.crud.abstraction;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<D, E> {

    D save(D dto);

    D getById(long id);

    D update(long id, D dto);

    D delete(long id);

    Page<D> getAll(E param, Pageable pageable);
}
