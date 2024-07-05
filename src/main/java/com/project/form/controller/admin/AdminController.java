package com.project.form.controller.admin;

import com.project.form.services.CurriculoService;
import com.project.form.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@AllArgsConstructor
public class AdminController {

    private CurriculoService curriculoService;

    @DeleteMapping("admin/remove/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        curriculoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("admin/curriculo/{id}")
    public ResponseEntity<?> getCurriculo(@PathVariable("id") Long id) throws MalformedURLException {
        Path curriculo = Paths.get(curriculoService.findCurriculoById(id));

        Resource resource = new UrlResource(curriculo.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            // Arquivo não encontrado
            return ResponseEntity.notFound().build();
        }

    }

}
