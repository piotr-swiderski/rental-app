package com.swiderski.carrental.pdfGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.swiderski.carrental.pdfGenerator.exception.PdfWriterException;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

public class PdfGenerator {

    private PdfGenerator() {
    }

    public static byte[] build(Collection<?> rows) {

        try {
            PdfPTable pdfPTable = TableBuilder.build(rows);
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            TableConfig tableConfig = new TableConfig(rows.iterator().next().getClass());

            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle(tableConfig.getTitle());
            document.add(new Phrase(tableConfig.getTitle()));
            document.add(pdfPTable);
            document.close();

            return out.toByteArray();
        } catch (DocumentException e) {
            throw new PdfWriterException();
        }
    }

}
