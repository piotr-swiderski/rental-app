package com.swiderski.carrental.mail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"${rest.api.version}"})
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mail")
    public String sendMail(@ModelAttribute MailSenderConfigurer mailSenderConfigurer) {
        mailService.sendEmailWithAttachment(mailSenderConfigurer);
        return "Send mail";
    }
}
