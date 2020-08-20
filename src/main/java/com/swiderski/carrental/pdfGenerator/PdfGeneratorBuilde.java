package com.swiderski.carrental.pdfGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.swiderski.carrental.pdfGenerator.annotation.PdfIgnoreFiled;
import com.swiderski.carrental.pdfGenerator.annotation.PdfTableName;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PdfGeneratorBuilde {



    public static final class PdfGeneratorConfigBuilder {
        private Class clazz;
        private String title;
        private Collection<?> rows;
        private final ByteArrayOutputStream out = new ByteArrayOutputStream();
        private final List<ColumnBuilder> columns = columnBuilder();
        private final Document document = new Document();

        private PdfGeneratorConfigBuilder() {
        }

        public static PdfGeneratorConfigBuilder PdfGeneratorConfig() {
            return new PdfGeneratorConfigBuilder();
        }

        public PdfGeneratorConfigBuilder withTitle() {
            if (!clazz.isAnnotationPresent(PdfTableName.class)) {
                title = "";
            } else {
                PdfTableName pdfTableName = (PdfTableName) clazz.getAnnotation(PdfTableName.class);
                title = pdfTableName.value();
            }
            return this;
        }

        public PdfGeneratorConfigBuilder withRows(Collection<?> rows) {
            this.rows = rows;
            this.clazz = rows.iterator().next().getClass();
            return this;
        }

        public PdfGeneratorBuilde build() {
            PdfGeneratorBuilde pdfGenerator = new PdfGeneratorBuilde();
            return pdfGenerator;

        }

        public List<ColumnBuilder> columnBuilder() {
            Class<?> superclass = clazz.getSuperclass();
            Field[] fields = clazz.getDeclaredFields();
            Field[] superClassFiled = superclass.getDeclaredFields();
            List<ColumnBuilder> columnBuilder = new ArrayList<>();
            for (Field field : superClassFiled) {
                if (!field.isAnnotationPresent(PdfIgnoreFiled.class)) {
                    columnBuilder.add(new ColumnBuilder(field));
                }
            }
            for (Field field : fields) {
                if (!field.isAnnotationPresent(PdfIgnoreFiled.class)) {
                    columnBuilder.add(new ColumnBuilder(field));
                }
            }
            return columnBuilder;
        }


        public byte[] makeGenerator() {

            Class<?> clazz = rows.iterator().next().getClass();
            try {
                PdfWriter.getInstance(document, out);
                document.open();
                if (!columns.isEmpty()) {
                    document.addTitle(title);
                    document.add(new Phrase(title));

                    PdfPTable pdfTable = getPdfTable(rows, columns);
                    document.add(pdfTable);
                } else {
                    document.add(new Phrase("Document is empty"));
                }
                document.close();
                return out.toByteArray();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            return out.toByteArray();

        }
    }

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
}
