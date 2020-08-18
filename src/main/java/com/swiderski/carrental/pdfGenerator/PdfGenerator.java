package com.swiderski.carrental.pdfGenerator;

import java.util.Collection;

public interface PdfGenerator {
    byte[] build(Collection<?> rows);
}
