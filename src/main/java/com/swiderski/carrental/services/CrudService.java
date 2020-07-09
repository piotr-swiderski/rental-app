package com.swiderski.carrental.services;

import java.util.List;

public interface CrudService<T> {

    T save(T dto);

    T getById(long id);

    List<T> getAll(int pageNo, int pageSize, String sortBy);

    T update(long id, T dto);

    T delete(long id);
}
