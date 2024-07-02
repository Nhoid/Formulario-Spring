package com.project.form.email;


import jakarta.mail.MessagingException;

public interface EmailService {

    void SendEmail(String to, String subject, String content);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException; ;

}
