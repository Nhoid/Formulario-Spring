package com.project.form.controller.form;


import com.project.form.services.CurriculoService;
import com.project.form.services.EmailService;
import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTOInput;
import com.project.form.services.StorageService;
import com.project.form.services.VagaService;
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

    private final CurriculoService curriculoService;

    private final StorageService storageService;

    private final EmailService emailService;

    private final VagaService vagaService;

    // RECEBE FORMULARIO
    @PostMapping("/form")
    public String register(@Valid CurriculoDTOInput curriculoDTOInput, HttpServletRequest request ) throws MessagingException {

        Curriculo curriculo = new Curriculo(curriculoDTOInput); // CRIA NOVO OBJETO CURRICULO

        curriculo.setVaga(vagaService.findById(curriculoDTOInput.getCargo())); // ADICIONA A VAGA SELECIONADA

        curriculo.setIpEnvio(request.getRemoteAddr()); // PEGA IP DO CANDIDATO

        curriculo.setArquivoUrl(storageService.store(curriculoDTOInput.getMultipartFile(), curriculo.getNome())); // GUARDA ARQUIVO ENVIADO EM UMA PASTA E RETORNA O ENDERECO

        curriculoService.save(curriculo); // SALVA CURRICULO

        emailService.mailResponse(curriculo); // ENVIA EMAIL

        return "redirect:/form";
    }

}
