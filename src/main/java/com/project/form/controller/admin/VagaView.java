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

    // PAGINA DE MANIPULACAO DE VAGAS
    @GetMapping("/admin/vaga")
    public String vaga(Model model) {

        List<Vaga> vagas = (List<Vaga>) vagaService.findAll(); // PEGA TODAS AS VAGAS

        vagas.forEach(vaga -> vaga.setCount(vaga.getCurriculos().size())); // PEGA QUANTOS INSCRITOS TEM EM CADA

        model.addAttribute("vagas", vagas); // ADICIONA AS VAGAS AS PAGINAS

        model.addAttribute("vaga", new VagaDTOInput()); // PEGA NOVA VAGA QUE  QUEIRA ADICIONAR

        return "vaga";
    }



}
