package com.project.form.services;


import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTOOutput;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EmailService {

    void SendEmail(String to, String subject, String content);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;

    void mailResponse(Curriculo curriculo) throws MessagingException;


}
