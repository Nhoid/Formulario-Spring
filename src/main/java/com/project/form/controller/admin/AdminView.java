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

    //INTERFACE DE ADMIN
    @GetMapping("/admin")
    public String admin(Model model){

        List<Vaga> vaga = (List<Vaga>) vagaService.findAll(); // PEGA TODAS AS VAGAS QUE EXITEM

        List<CurriculoDTOOutput> curriculos = curriculoService.findAll(); //PEGA TODOS OS CURRICULOS

        model.addAttribute("curriculos", curriculos); // COLOCA NA PAGINA

        model.addAttribute("vagas", vaga); // COLOCA NA PAGINA

        return "admin";
    }

    // FILTRAR CANDIDATOS
    @GetMapping("/admin/filter")
    public String adminFilter(@RequestParam String dataInicio, // DATA DE INICIO D FILTRO
                              @RequestParam String dataFim, // DATA DO FIM DO FILTRO
                              @RequestParam(required = false, defaultValue = "-1") Long vaga, // VAGA QUE SERA FILTRADA
                              @RequestParam(required = false, defaultValue = "false") Boolean vagaquali, // SE VAI QUERER OU NAO CANDIDATOS DEQUALIFICADOS
                              Model model) {

        // INSTANCIA DE DATAS
        Instant from;
        Instant to;

        // PEGA DATAS FORNECIDAS OU DEFINE PADROES
        if (dataInicio.isBlank()) from = toInstant("2000-01-01");
        else from = toInstant(dataInicio);

        if (dataFim.isBlank()) to = Instant.now();
        else to = toInstant(dataFim);

        // PEGA TODOS OS CURRICULOS COM BASE NOS FILTROS
        List<CurriculoDTOOutput> curriculos = curriculoService.findAllByDate(from, to, vagaquali);

        // FILTRA POR VAGA, SE FOR NECESSARIO
        if (vaga != -1){
            Vaga vagaSelecionada = vagaService.findById(vaga);

            curriculos = curriculos.stream().filter(curriculoDTOOutput -> Objects.equals(curriculoDTOOutput.getCargoDesejado(), vagaSelecionada.getNomeDaVaga())).toList();
        }

        model.addAttribute("vagas", vagaService.findAll()); // ADICIONA NA PAGINA

        model.addAttribute("curriculos", curriculos); // ADICIONA NA PAGINA

        return "admin";
    }

    // FUNCAO PARA TRANSFORMAR STRING EM INSTANT
    private Instant toInstant(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(data, formatter);

        return localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}
