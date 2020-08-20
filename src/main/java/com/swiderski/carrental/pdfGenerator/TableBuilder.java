package com.swiderski.carrental.pdfGenerator;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.swiderski.carrental.pdfGenerator.exception.PdfEmptyCollectionException;

import java.util.Collection;

public class TableBuilder {

    public static PdfPTable build(Collection<?> rows) {

        if (rows.isEmpty()) {
            throw new PdfEmptyCollectionException();
        }

        TableConfig tableConfig = new TableConfig(rows.iterator().next().getClass());
        PdfPTable pdfTable = new PdfPTable(tableConfig.getColumnList().size());

        for (ColumnConfig c : tableConfig.getColumnList()) {
            PdfPCell cell = new PdfPCell(new Phrase(c.getName()));
            pdfTable.addCell(cell);
        }

        for (Object row : rows) {
            for (ColumnConfig c : tableConfig.getColumnList()) {
                PdfPCell cell = new PdfPCell(new Phrase(c.get(row)));
                pdfTable.addCell(cell);
            }
        }
        return pdfTable;
    }

}
