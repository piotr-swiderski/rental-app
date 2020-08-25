package com.swiderski.carrental.xlsxGenerator;

import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

public class XlsxGenerator {

    @SneakyThrows
    public static byte[] build(Collection<?> rows) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XlsxBuilder xlsxBuilder = new XlsxBuilder(rows);
        XSSFWorkbook workbook = xlsxBuilder.build();

        workbook.write(out);
        return out.toByteArray();
    }
}

