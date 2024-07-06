package com.project.form.controller.admin;

import com.project.form.model.Curriculo;
import com.project.form.model.Vaga;
import com.project.form.model.dto.VagaDTOInput;
import com.project.form.services.CurriculoService;
import com.project.form.services.VagaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
@AllArgsConstructor
public class VagaController {

    private final VagaService vagaService;

    private final CurriculoService curriculoService;

    // ADICIONAR UMA NOVA VAGA
    @PostMapping("admin/vaga/add")
    public String addVaga(@Valid VagaDTOInput vaga) {

        Vaga novaVaga =  new Vaga(vaga); // CRIA UMA NOVA INSTANCIA

        vagaService.save(novaVaga); // SALVA

        return "redirect:/admin/vaga";
    }

    // ATIVAR/DESATIVAR UMA VAGA
    @PostMapping("admin/vaga/chagestatus/{id}")
    public String desativarVaga(@PathVariable("id") Long id){

        Vaga vaga = vagaService.findById(id); // PROCURA A VAGA

        Set<Curriculo> curriculos = vaga.getCurriculos(); // PEGA TODOS OS CURRICULOS INSCRITOS NA VAGA

        curriculos.forEach(curriculo -> curriculo.setDesqualificado(true)); // DESQUALIFICA TODOS OS CURRICULOS

        curriculos.forEach(curriculoService::save); // ATUALIZA OS CURRICULOS

        vaga.chageStatus(); // MUDA STATUS DA VAGA

        vagaService.save(vaga); // ATUALIZA A VAGA

        return "redirect:/admin/vaga";
    }


}
