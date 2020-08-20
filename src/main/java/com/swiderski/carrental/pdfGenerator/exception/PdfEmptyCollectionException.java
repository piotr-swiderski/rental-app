package com.swiderski.carrental.pdfGenerator.exception;

public class PdfEmptyCollectionException extends RuntimeException {

    public PdfEmptyCollectionException() {
        super("Can't build table from empty collection");
    }
}
