package com.swiderski.carrental.pdfGenerator.annotation;

import com.swiderski.carrental.pdfGenerator.utils.ColorEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnRow {
    String value() default "";

    String filed() default "";

    ColorEnum background() default ColorEnum.WHITE;

    ColorEnum textColor() default ColorEnum.BLACK;
}


