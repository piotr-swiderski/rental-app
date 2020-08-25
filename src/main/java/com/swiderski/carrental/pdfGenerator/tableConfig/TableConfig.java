package com.swiderski.carrental.pdfGenerator.tableConfig;

import com.swiderski.carrental.pdfGenerator.annotation.ColumnRow;
import com.swiderski.carrental.pdfGenerator.annotation.JoinColumns;
import com.swiderski.carrental.pdfGenerator.annotation.PdfIgnoreFiled;
import com.swiderski.carrental.pdfGenerator.annotation.PdfTableName;
import com.swiderski.carrental.pdfGenerator.exception.IlegallJoinColumnsAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableConfig {

    private List<ColumnConfig> columnConfigs;
    private String title;

    public TableConfig(Class<?> clazz) {
        this.columnConfigs = getColumnBuilder(clazz);
        this.title = getTitle(clazz);
    }

    public List<ColumnConfig> getColumnConfig() {
        return columnConfigs;
    }

    public String getTitle() {
        return title;
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
                if (field.isAnnotationPresent(JoinColumns.class)) {
                    columns.addAll(multipleColumns(field));
                } else {
                    columns.add(new ColumnConfig(field));
                }
            }
        }
        return columns;
    }

    private static Collection<? extends ColumnConfig> multipleColumns(Field field) {
        JoinColumns annotation = field.getAnnotation(JoinColumns.class);
        ColumnRow[] columnRows = annotation.value();
        List<ColumnConfig> columnConfigs = new ArrayList<>();
        for (ColumnRow columnRow : columnRows) {
            Field declaredField;
            try {
                declaredField = field.getType().getDeclaredField(columnRow.filed());
            } catch (NoSuchFieldException e) {
                throw new IlegallJoinColumnsAnnotation(e.getMessage());
            }
            columnConfigs.add(new ColumnConfig(field, declaredField, columnRow));
        }
        return columnConfigs;
    }
}
