package com.swiderski.carrental.mail;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface MailService {

    CompletableFuture<Void> sendEmailWithAttachment(MailSenderConfigurer mailSenderConfigurer);
}
