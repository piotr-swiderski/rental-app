package com.swiderski.carrental.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailWithAttachment(MailSenderConfigurer mailSenderConfigurer) {
        MailWithAttachment mail = new MailWithAttachment(mailSenderConfigurer);
        javaMailSender.send(mail);
    }
}
