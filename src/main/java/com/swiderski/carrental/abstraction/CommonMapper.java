package com.swiderski.carrental.abstraction;

import java.util.List;
import java.util.Set;

public interface CommonMapper<E extends AbstractEntity, D> {

    D toDto(E e);

    E fromDto(D dto);

    Set<D> toSetDto(Set<E> e);

    List<D> toListDto(List<E> e);
}
