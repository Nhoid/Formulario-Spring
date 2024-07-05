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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
@AllArgsConstructor
public class VagaController {

    private final VagaService vagaService;

    private final CurriculoService curriculoService;

    @PostMapping("admin/vaga/add")
    public String addVaga(@Valid VagaDTOInput vaga) {

        Vaga novaVaga =  new Vaga(vaga);

        vagaService.save(novaVaga);

        return "redirect:/admin/vaga";
    }

    @DeleteMapping("admin/vaga/remove/{id}")
    public ResponseEntity<?> removeVaga(@PathVariable("id") Long id){

        Set<Curriculo> curriculos = vagaService.findById(id).getCurriculos();

        curriculos.forEach(curriculo -> curriculoService.delete(curriculo.getId()));

        vagaService.delete(id);

        return ResponseEntity.ok().build();
    }


}
