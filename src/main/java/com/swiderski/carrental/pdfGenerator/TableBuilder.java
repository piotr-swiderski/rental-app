package com.swiderski.carrental.pdfGenerator;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.swiderski.carrental.pdfGenerator.exception.PdfEmptyCollectionException;
import com.swiderski.carrental.pdfGenerator.tableConfig.ColumnConfig;
import com.swiderski.carrental.pdfGenerator.tableConfig.TableConfig;
import com.swiderski.carrental.pdfGenerator.tableConfig.TableConfigFactory;

import java.util.Collection;

public class TableBuilder {

    public static PdfPTable build(Collection<?> rows) {

        if (rows.isEmpty()) {
            throw new PdfEmptyCollectionException();
        }

        TableConfig tableConfig = TableConfigFactory.getTable(rows.iterator().next().getClass());
        PdfPTable pdfTable = new PdfPTable(tableConfig.getColumnConfig().size());

        for (ColumnConfig c : tableConfig.getColumnConfig()) {
            PdfPCell cell = new PdfPCell(c.getHeader());
            pdfTable.addCell(cell);
        }

        for (Object row : rows) {
            for (ColumnConfig c : tableConfig.getColumnConfig()) {
                PdfPCell cell = c.getPdfPCell(row);
                pdfTable.addCell(cell);
            }
        }
        return pdfTable;
    }

}
