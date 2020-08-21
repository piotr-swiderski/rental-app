package com.swiderski.carrental.crud.abstraction;

public interface EmailSenderService<E extends CommonParam> {

    byte[] getPdfReport(E param);
}
