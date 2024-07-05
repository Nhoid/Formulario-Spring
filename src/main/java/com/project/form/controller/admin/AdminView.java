package com.project.form.controller.admin;


import com.project.form.model.Vaga;
import com.project.form.model.dto.CurriculoDTOOutput;
import com.project.form.services.CurriculoService;
import com.project.form.services.VagaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class AdminView {

    private CurriculoService curriculoService;

    private VagaService vagaService;


    @GetMapping("/admin")
    public String admin(Model model){

        List<Vaga> vaga = (List<Vaga>) vagaService.findAll();

        List<CurriculoDTOOutput> curriculos = curriculoService.findAll();

        model.addAttribute("curriculos", curriculos);

        model.addAttribute("vagas", vaga);

        return "admin";
    }

    @GetMapping("/admin/filter")
    public String adminFilter(@RequestParam String dataInicio,
                              @RequestParam String dataFim,
                              @RequestParam(required = false, defaultValue = "-1") Long vaga,
                              Model model) {

        Instant from;
        Instant to;

        if (dataInicio.isBlank()) from = toInstant("2000-01-01");
        else from = toInstant(dataInicio);

        if (dataFim.isBlank()) to = Instant.now();
        else to = toInstant(dataFim);

        List<CurriculoDTOOutput> curriculos = curriculoService.findAllByDate(from, to);

        if (vaga != -1){
            Vaga vagaSelecionada = vagaService.findById(vaga);

            curriculos = curriculos.stream().filter(curriculoDTOOutput -> Objects.equals(curriculoDTOOutput.getCargoDesejado(), vagaSelecionada.getNomeDaVaga())).toList();

        }

        model.addAttribute("vagas", vagaService.findAll());

        model.addAttribute("curriculos", curriculos);

        return "admin";
    }

    private Instant toInstant(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(data, formatter);

        return localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}
