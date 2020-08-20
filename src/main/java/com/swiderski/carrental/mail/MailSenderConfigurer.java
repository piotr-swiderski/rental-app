package com.swiderski.carrental.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailSenderConfigurer {
    private String sendTo;
    private String sendFrom;
    private String filename;
    private String subject;
    private String message;
    private byte[] file;

    public static final class MailWithAttachmentBuilder {
        private String sendTo;
        private String sendFrom = "example@example.com";
        private String subject = "Email subject";
        private byte[] file;
        private String filename = "report.pdf";
        private String message = "Report";

        private MailWithAttachmentBuilder() {
        }

        public static MailWithAttachmentBuilder aMailWithAttachment() {
            return new MailWithAttachmentBuilder();
        }

        public MailWithAttachmentBuilder withSendTo(String sendTo) {
            this.sendTo = sendTo;
            return this;
        }

        public MailWithAttachmentBuilder withSendFrom(String sendFrom) {
            this.sendFrom = sendFrom;
            return this;
        }

        public MailWithAttachmentBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailWithAttachmentBuilder withFile(byte[] file) {
            this.file = file;
            return this;
        }

        public MailWithAttachmentBuilder withFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public MailWithAttachmentBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public MailSenderConfigurer build() {
            MailSenderConfigurer mailSenderConfigurer = new MailSenderConfigurer();
            mailSenderConfigurer.setSendTo(sendTo);
            mailSenderConfigurer.setSendFrom(sendFrom);
            mailSenderConfigurer.setSubject(subject);
            mailSenderConfigurer.setFile(file);
            mailSenderConfigurer.setMessage(message);
            mailSenderConfigurer.setFilename(filename);
            return mailSenderConfigurer;
        }
    }
}
