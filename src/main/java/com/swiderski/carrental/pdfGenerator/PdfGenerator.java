package com.swiderski.carrental.pdfGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.swiderski.carrental.pdfGenerator.exception.PdfWriterException;
import com.swiderski.carrental.pdfGenerator.tableConfig.TableConfig;
import com.swiderski.carrental.pdfGenerator.tableConfig.TableConfigFactory;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

@Log4j2
public class PdfGenerator {

    private PdfGenerator() {
    }

    public static byte[] build(Collection<?> rows) {

        try {
            PdfPTable pdfPTable = TableBuilder.build(rows);
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Class<?> clazz = rows.iterator().next().getClass();
            TableConfig tableConfig = TableConfigFactory.getTable(clazz);

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
