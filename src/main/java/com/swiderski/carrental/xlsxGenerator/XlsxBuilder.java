package com.swiderski.carrental.xlsxGenerator;

import com.swiderski.carrental.pdfGenerator.tableConfig.ColumnConfig;
import com.swiderski.carrental.pdfGenerator.tableConfig.TableConfig;
import com.swiderski.carrental.pdfGenerator.tableConfig.TableConfigFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;
import java.util.List;

public class XlsxBuilder {

    private final TableConfig tableConfig;
    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;

    public XlsxBuilder(Collection<?> rows) {
        this.tableConfig = TableConfigFactory.getTable(rows.iterator().next().getClass());
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(tableConfig.getTitle());
        buildHeader();
        fillValue(rows);
    }

    public XSSFWorkbook build(){
        return workbook;
    }

    private void buildHeader() {
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight((short) 4);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        Row titleRow = sheet.createRow(1);
        Cell titleRowCell = titleRow.createCell(0);
        titleRowCell.setCellValue("Xlsx report");

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(3);
        List<ColumnConfig> columnConfig = tableConfig.getColumnConfig();
        for (int col = 0; col < columnConfig.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columnConfig.get(col).getHeaderValue());
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void fillValue(Collection<?> rows) {
        CellStyle ageCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

        int rowIdx = 4;
        for (Object row : rows) {
            int colIndex = 0;
            Row excelRow = sheet.createRow(rowIdx);
            for (ColumnConfig c : tableConfig.getColumnConfig()) {
                Cell cell = excelRow.createCell(colIndex);
                cell.setCellValue(c.getValue(row));
                colIndex++;
            }
            rowIdx++;
        }
    }
}
