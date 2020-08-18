package com.swiderski.carrental.crud.abstraction;

import com.swiderski.carrental.pdfGenerator.annotation.ColumnRow;

import javax.validation.constraints.PositiveOrZero;

public abstract class AbstractDto {

    @PositiveOrZero
    @ColumnRow("Id")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
