package com.swiderski.carrental.pdfGenerator.service;

import java.util.Collection;

public interface PdfService {
    byte[] generatePdf(Collection<?> list);
}
