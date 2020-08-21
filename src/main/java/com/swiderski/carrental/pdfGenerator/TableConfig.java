package com.swiderski.carrental.pdfGenerator;

import com.swiderski.carrental.pdfGenerator.annotation.PdfIgnoreFiled;
import com.swiderski.carrental.pdfGenerator.annotation.PdfTableName;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TableConfig {

    private List<ColumnConfig> columnList;
    private String title;

    public TableConfig(Class<?> clazz) {
        this.columnList = getColumnBuilder(clazz);
        this.title = getTitle(clazz);
    }


    private static String getTitle(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(PdfTableName.class)) {
            return "";
        } else {
            PdfTableName pdfTableName = clazz.getAnnotation(PdfTableName.class);
            return pdfTableName.value();
        }
    }

    private static List<ColumnConfig> getColumnBuilder(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();

        Field[] fields = clazz.getDeclaredFields();
        Field[] superClassFiled = superclass.getDeclaredFields();
        List<ColumnConfig> columns = new ArrayList<>();

        for (Field field : superClassFiled) {
            if (!field.isAnnotationPresent(PdfIgnoreFiled.class)) {
                columns.add(new ColumnConfig(field));
            }
        }
        for (Field field : fields) {
            if (!field.isAnnotationPresent(PdfIgnoreFiled.class)) {
                columns.add(new ColumnConfig(field));
            }
        }
        return columns;
    }
}
