package com.swiderski.carrental.pdfGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.swiderski.carrental.pdfGenerator.annotation.PdfIgnoreFiled;
import com.swiderski.carrental.pdfGenerator.annotation.PdfTableName;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PdfGenerator {

    @SneakyThrows
    public static byte[] build(Collection<?> rows) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Class<?> clazz = rows.iterator().next().getClass();

        PdfWriter.getInstance(document, out);
        document.open();
        List<ColumnBuilder> columns = getColumnBuilder(clazz);

        if (!columns.isEmpty()) {
            String title = getTitle(clazz);
            document.addTitle(title);
            document.add(new Phrase(title));

            PdfPTable pdfTable = getPdfTable(rows, columns);
            document.add(pdfTable);
        } else {
            document.add(new Phrase("Document is empty"));
        }
        document.close();
        return out.toByteArray();

    }

    @NotNull
    private static List<ColumnBuilder> getColumnBuilder(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<ColumnBuilder> columns = new ArrayList<>();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(PdfIgnoreFiled.class)) {
                columns.add(new ColumnBuilder(field));
            }
        }
        return columns;
    }

    @NotNull
    private static PdfPTable getPdfTable(Collection<?> rows, List<ColumnBuilder> columns) {
        PdfPTable pdfTable = new PdfPTable(columns.size());

        for (ColumnBuilder c : columns) {
            PdfPCell cell = new PdfPCell(new Phrase(c.getName()));
            pdfTable.addCell(cell);
        }

        for (Object row : rows) {
            for (ColumnBuilder c : columns) {
                PdfPCell cell = new PdfPCell(new Phrase(c.get(row)));
                pdfTable.addCell(cell);
            }
        }

        pdfTable.setWidthPercentage(100);
        return pdfTable;
    }

    @NotNull
    private static String getTitle(Class clazz) {

        if (!clazz.isAnnotationPresent(PdfTableName.class)) {
            return "";
        } else {
            PdfTableName pdfTableName = (PdfTableName) clazz.getAnnotation(PdfTableName.class);
            return pdfTableName.value();
        }

    }

}
