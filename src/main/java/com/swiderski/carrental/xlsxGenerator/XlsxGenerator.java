package com.swiderski.carrental.xlsxGenerator;

import com.swiderski.carrental.xlsxGenerator.annotation.XlsxIgnoreField;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XlsxGenerator {

    @SneakyThrows
    public static byte[] customersToExcel(Collection<?> rows) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Report");
        sheet.autoSizeColumn(40);
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight((short) 4);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        Row titleRow = sheet.createRow(1);
        Cell titleRowCell = titleRow.createCell(0);
        titleRowCell.setCellValue("Xlsx report");


        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(3);
        Class<?> clazz = rows.iterator().next().getClass();
        List<ColumnDefinition> columns = getColumnDefinitions(clazz);

        for (int col = 0; col < columns.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columns.get(col).getName());
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle ageCellStyle = workbook.createCellStyle();
        ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

        int rowIdx = 4;
        for (Object row : rows) {
            int colIndex = 0;
            Row excelRow = sheet.createRow(rowIdx);
            for (ColumnDefinition c : columns) {
                Cell cell = excelRow.createCell(colIndex);
                cell.setCellValue(c.get(row));
                colIndex++;
            }
            rowIdx++;
        }

        workbook.write(out);
        return out.toByteArray();
    }

    @NotNull
    private static List<ColumnDefinition> getColumnDefinitions(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<ColumnDefinition> columns = new ArrayList<>();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(XlsxIgnoreField.class)) {
                ColumnDefinition columnDefinition = new ColumnDefinition(field);
                columns.add(columnDefinition);
            }
        }
        return columns;
    }
}

