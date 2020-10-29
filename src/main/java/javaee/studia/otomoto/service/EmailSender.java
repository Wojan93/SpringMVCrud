package javaee.studia.otomoto.service;

public interface EmailSender {

    void sendEmail(String to, String subject, String content);
}
