package com.project.form.controller;


import com.project.form.email.EmailService;
import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTO;
import com.project.form.services.CurriculoServiceImpl;
import com.project.form.storage.StorageService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
@AllArgsConstructor
public class FormController {

    private final CurriculoServiceImpl curriculoServiceImpl;

    private final StorageService storageService;

    private final EmailService emailService;

    private final Logger logger = Logger.getLogger(FormController.class.getName());

    @PostMapping("/form/send")
    public String register(@Valid CurriculoDTO curriculoDTO, HttpServletRequest request ) throws MessagingException {

        Curriculo curriculo = new Curriculo(curriculoDTO);

        curriculo.setIpEnvio(request.getRemoteAddr());

        curriculo.setArquivoUrl(storageService.store(curriculoDTO.getMultipartFile()));

        curriculoServiceImpl.save(curriculo);

        mailSender(curriculo);

        return "redirect:/form";
    }

    private void mailSender(Curriculo curriculo) throws MessagingException {

        logger.info("Sending email");

        logger.info(curriculo.getArquivoUrl());

        emailService.sendMessageWithAttachment(
                curriculo.getEmail(),
                "Confirmação de Inscrição na Vaga",
                "Olá " + curriculo.getNome() + ",\n" +
                        "\n" +
                        "Confirmamos o recebimento da sua inscrição para a vaga de " + curriculo.getCargoDesejado() + ". Agradecemos seu interesse.\n" +
                        "\n" +
                        "Entraremos em contato caso seu perfil corresponda aos requisitos da vaga.\n" +
                        "\n" +
                        "Atenciosamente,\n" +
                        "\n" +
                        "Empresa",
                curriculo.getArquivoUrl()
                );
    }



}
