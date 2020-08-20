package com.swiderski.carrental.mail.exception;

public class EmailSenderException extends RuntimeException {

    public EmailSenderException(String from, String to) {
        super("Failed send email from" +  from + " to " + to);
    }
}
