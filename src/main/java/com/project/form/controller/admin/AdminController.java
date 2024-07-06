package com.project.form.controller.admin;

import com.project.form.model.Curriculo;
import com.project.form.services.CurriculoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@AllArgsConstructor
public class AdminController {

    private CurriculoService curriculoService;

    // QUALIFICAR/DESQUALIFICAR CANDIDATO
    @PostMapping("admin/curriculo/changestatus/{id}")
    public String chageStatus(@PathVariable Long id){

        Curriculo curriculo = curriculoService.findById(id); // PEGA CANDIDATO

        curriculo.changeStatus(); // MUDA STATUS

        curriculoService.save(curriculo); // SALVA

        return "redirect:/admin";
    }

    //BAIXAR CURRICULO DO CANDIDADO
    @GetMapping("admin/curriculo/{id}")
    public ResponseEntity<?> getCurriculo(@PathVariable("id") Long id) throws MalformedURLException {

        Path curriculo = Paths.get(curriculoService.findCurriculoById(id)); // PEGA PATH DO CURRICULO

        Resource resource = new UrlResource(curriculo.toUri()); // PEGA COMO RESOURCE

        if (resource.exists() && resource.isReadable()) { // VERIFICACAO SE EXISTE
            return ResponseEntity.ok() // RETORNA
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }

            // ARQUIVO NAO ENCONTRADO
            return ResponseEntity.notFound().build();
    }

}
