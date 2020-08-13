package com.swiderski.carrental.crud.abstraction;

import javax.validation.constraints.PositiveOrZero;

public abstract class AbstractDto {

    @PositiveOrZero
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
