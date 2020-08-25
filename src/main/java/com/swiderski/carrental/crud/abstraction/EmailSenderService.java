package com.swiderski.carrental.crud.abstraction;

import com.swiderski.carrental.mail.MailSenderConfigurer;

import java.util.concurrent.CompletableFuture;

public interface EmailSenderService<E extends CommonParam> {

    byte[] getPdfReport(E param);

    byte[] getXlsxReport(E param);

    CompletableFuture<String> sendPdfEmail(MailSenderConfigurer mailSenderConfigurer, E param);
}
