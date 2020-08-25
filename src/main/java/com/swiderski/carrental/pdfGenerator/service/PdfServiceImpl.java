package com.swiderski.carrental.pdfGenerator.service;

import com.swiderski.carrental.pdfGenerator.PdfGenerator;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] generatePdf(Collection<?> list) {
        return PdfGenerator.build(list);
    }
}
