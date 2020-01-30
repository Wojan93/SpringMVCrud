package javaee.studia.otomoto.email;

/**
 *
 * @author Mateusz Wilk
 */

public interface EmailSender {

    void sendEmail(String to, String subject, String content);
}
