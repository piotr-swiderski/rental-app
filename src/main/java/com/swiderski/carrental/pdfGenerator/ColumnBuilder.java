package com.swiderski.carrental.pdfGenerator;

import com.swiderski.carrental.pdfGenerator.annotation.ColumnRow;

import java.lang.reflect.Field;

public class ColumnBuilder {

    private final Field field;
    private final String name;


    public ColumnBuilder(Field field) {
        this.field = field;

        ColumnRow columnRow = field.getAnnotation(ColumnRow.class);
        if (columnRow == null) {
            name = field.getName();
        } else {
            name = columnRow.value();
        }

        field.setAccessible(true);
    }

    public String getName() {
        return name;
    }

    public String get(Object row) {
        try {
            Object value = field.get(row);
            if (value == null) {
                return "";
            }
            return value.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}