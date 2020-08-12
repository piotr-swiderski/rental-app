package com.swiderski.carrental.crud.abstraction;

public interface CommonService<D> {

    D save(D dto);

    D getById(long id);

    D update(long id, D dto);

    D delete(long id);
}
