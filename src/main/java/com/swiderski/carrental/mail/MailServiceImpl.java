package com.swiderski.carrental.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public CompletableFuture<Void> sendEmailWithAttachment(MailSenderConfigurer mailSenderConfigurer) {
        MailWithAttachment mail = new MailWithAttachment(mailSenderConfigurer);
        javaMailSender.send(mail);
        System.out.println("send");
        return CompletableFuture.completedFuture(null);
    }
}
