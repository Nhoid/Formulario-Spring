package com.project.form.controller.form;


import com.project.form.model.Vaga;
import com.project.form.model.dto.CurriculoDTOInput;
import com.project.form.services.VagaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class FormView {

    private final VagaService vagaService;

    // INTERFACE DO FORMULARIO
    @GetMapping("/form")
    public String form(Model model) {

        Long defaultVagaId = 1L;

        model.addAttribute("curriculo", new CurriculoDTOInput()); // ADICIONA INPUT DE DADOS

        model.addAttribute("vagas", vagaService.findAllAtiva()); // EXIBE VAGAS ATIVAS
        model.addAttribute("defaultVaga", defaultVagaId);

        return "form" ;
    }

}
