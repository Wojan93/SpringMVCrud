package javaee.studia.otomoto.service;

/**
 *
 * @author Mateusz Wilk
 */

public interface EmailSender {

    void sendEmail(String to, String subject, String content);
}
