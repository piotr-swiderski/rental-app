package com.swiderski.carrental.abstraction;

import java.util.List;

public interface CommonService<D> {

    D save(D dto);

    D getById(long id);

    List<D> getAll(int pageNo, int pageSize, String sortBy);

    D update(long id, D dto);

    D delete(long id);
}
