package com.project.form.controller.admin;

import com.project.form.model.Vaga;
import com.project.form.model.dto.VagaDTOInput;
import com.project.form.services.VagaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@AllArgsConstructor
public class VagaView {

    private final VagaService vagaService;

    private final Logger logger = Logger.getLogger(VagaView.class.getName());

    @GetMapping("/admin/vaga")
    public String vaga(Model model) {

        List<Vaga> vagas = (List<Vaga>) vagaService.findAll();

        vagas.forEach(vaga -> vaga.setCount(vaga.getCurriculos().size()));

        model.addAttribute("vagas", vagas);

        model.addAttribute("vaga", new VagaDTOInput());

        return "vaga";
    }



}
