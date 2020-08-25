package com.swiderski.carrental.pdfGenerator.tableConfig;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.swiderski.carrental.pdfGenerator.ColorEnum;
import com.swiderski.carrental.pdfGenerator.annotation.ColumnRow;

import java.lang.reflect.Field;

public class ColumnConfig {

    private Field field;
    private String name;
    private Field superFiled;
    private PdfPCell pdfPCell;
    private Font font = new Font();


    public ColumnConfig(Field field) {
        this.field = field;
        this.pdfPCell = new PdfPCell();

        field.setAccessible(true);
        setColumnDefinition(field.getAnnotation(ColumnRow.class));
    }

    public ColumnConfig(Field superField, Field declaredField, ColumnRow columnDefinition) {
        this.superFiled = superField;
        this.field = declaredField;
        this.pdfPCell = new PdfPCell();

        field.setAccessible(true);
        superFiled.setAccessible(true);
        setColumnDefinition(columnDefinition);
    }

    public PdfPCell get(Object row) {
        Phrase phrase = new Phrase(getRowValue(row));
        phrase.setFont(font);
        pdfPCell.setPhrase(phrase);
        return pdfPCell;
    }

    private void setColumnDefinition(ColumnRow columnDefinition) {
        if (columnDefinition != null) {
            setColumnName(columnDefinition);
            setColumnBackground(columnDefinition);
            setFont(columnDefinition);
        } else {
            this.name = field.getName();
        }
    }

    private void setColumnName(ColumnRow columnRow) {
        if (columnRow.value().isEmpty()) {
            this.name = field.getName();
        } else {
            this.name = columnRow.value();
        }
    }

    private void setColumnBackground(ColumnRow columnRow) {
        ColorEnum background = columnRow.background();
        pdfPCell.setBackgroundColor(background.get());
    }

    private void setFont(ColumnRow columnRow) {
        ColorEnum textColor = columnRow.textColor();
        this.font.setColor(textColor.get());
    }

    public PdfPCell getHeader() {
        Phrase header = new Phrase(name);
        header.setFont(font);
        pdfPCell.setPhrase(header);
        return pdfPCell;
    }


    private String getRowValue(Object row) {
        Object value = getFiledValue(row);
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    private Object getFiledValue(Object row) {
        Object value = null;
        try {
            if (superFiled != null) {
                Object superFieldRow = superFiled.get(row);
                value = field.get(superFieldRow);
            } else {
                value = field.get(row);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }
}
