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

    private final Logger logger = Logger.getLogger(FormController.class.getName());

    @PostMapping("/form/send")
    public String register(@Valid CurriculoDTOInput curriculoDTOInput, HttpServletRequest request ) throws MessagingException {

        Curriculo curriculo = new Curriculo(curriculoDTOInput);

        curriculo.setVaga(vagaService.findById(curriculoDTOInput.getCargo()));

        curriculo.setIpEnvio(request.getRemoteAddr());

        curriculo.setArquivoUrl(storageService.store(curriculoDTOInput.getMultipartFile(), curriculo.getNome()));

        curriculoService.save(curriculo);

        emailService.mailResponse(curriculo);

        return "redirect:/form";
    }

}
