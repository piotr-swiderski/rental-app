package com.swiderski.carrental.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async("EmailSender-")
    public CompletableFuture<Void> sendEmailWithAttachment(MailSenderConfigurer mailSenderConfigurer) {
        MailWithAttachment mail = new MailWithAttachment(mailSenderConfigurer);
        javaMailSender.send(mail);
        System.out.println("send");
        return CompletableFuture.completedFuture(null);
    }
}
