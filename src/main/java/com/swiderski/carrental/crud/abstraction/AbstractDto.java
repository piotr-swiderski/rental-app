package com.swiderski.carrental.crud.abstraction;

public abstract class AbstractDto {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
