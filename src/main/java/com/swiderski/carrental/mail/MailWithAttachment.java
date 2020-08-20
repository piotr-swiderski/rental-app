package com.swiderski.carrental.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Getter
@Setter
public class MailWithAttachment implements MimeMessagePreparator {

    private final MailSenderConfigurer mailSenderConfigurer;

    public MailWithAttachment(MailSenderConfigurer mailSenderConfigurer) {
        this.mailSenderConfigurer = mailSenderConfigurer;
    }


    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        mimeMessage.setFrom(mailSenderConfigurer.getSendFrom());
        mimeMessage.setSubject(mailSenderConfigurer.getSubject());
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailSenderConfigurer.getSendTo()));

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.addAttachment(mailSenderConfigurer.getFilename(), new ByteArrayResource(mailSenderConfigurer.getFile()));
        helper.setText(mailSenderConfigurer.getMessage());
    }
}
