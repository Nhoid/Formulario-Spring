package com.project.form.services.impl;

import com.project.form.model.Curriculo;
import com.project.form.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.logging.Logger;

//EMAIL SERVICE
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());

    // ENVIA EMAIL BASICO
    @Override
    public void SendEmail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);

    }

    // ENVIA EMAIL COM ANEXO
    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Attachment", file);

        javaMailSender.send(message);
    }

    //ENVIA EMAIL PARA O CANDIDADO
    @Override
    @Async("taskExecutor")
    public void mailResponse(Curriculo curriculo) throws MessagingException {

        sendMessageWithAttachment(
                curriculo.getEmail(),
                "Confirmação de Inscrição na Vaga",
                "Prezado(a) " + curriculo.getNome() + ",\n\n"
                        + "Recebemos o seu currículo para a vaga de " + curriculo.getVaga().getNomeDaVaga() + ". Agradecemos o seu interesse em fazer parte da nossa equipe. Abaixo estão os detalhes do seu envio:\n\n"
                        + "- Nome: " + curriculo.getNome() + "\n"
                        + "- E-mail: " + curriculo.getEmail() + "\n"
                        + "- Telefone: " + curriculo.getTelefone() + "\n"
                        + "- Escolaridade: " + curriculo.getEscolaridade() + "\n"
                        + "- Observações: " + curriculo.getObservacoes() + "\n\n"
                        + "O arquivo do seu currículo está anexado a este e-mail.\n\n"
                        + "Estamos revisando cuidadosamente o seu perfil e entraremos em contato em breve para informá-lo(a) sobre o status do processo seletivo.\n\n"
                        + "Atenciosamente,\n\n"
                        + "Empresa",
                curriculo.getArquivoUrl()
        );

    }
}
