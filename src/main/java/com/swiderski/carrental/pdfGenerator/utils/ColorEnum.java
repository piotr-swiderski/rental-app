package com.swiderski.carrental.pdfGenerator.utils;

import com.itextpdf.text.BaseColor;

public enum ColorEnum {
    WHITE(BaseColor.WHITE),
    CYAN(BaseColor.CYAN),
    BLUE(BaseColor.BLUE),
    BLACK(BaseColor.BLACK),
    GREEN(BaseColor.GREEN),
    GRAY(BaseColor.GRAY),
    RED(BaseColor.RED),
    YELLOW(BaseColor.YELLOW);


    BaseColor color;

    ColorEnum(BaseColor color) {
        this.color = color;
    }

    public BaseColor get() {
        return this.color;
    }
}
