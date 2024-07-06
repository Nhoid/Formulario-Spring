package com.project.form.model.dto;

import com.project.form.util.annotation.FileExtension;
import com.project.form.util.annotation.NotEmptyFile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

// OBJETO QUE RECEBE OS DADOS ENVIADOS
@Data
public class CurriculoDTOInput {

        @NotBlank(message = "Nome não pode ser vazio")
        private String nome;

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Formato de email inválido")
        private String email;

        @NotBlank(message = "Telefone não pode ser vazio")
        private String telefone;

        private Long cargo;

        @NotBlank(message = "Escolha seu nivel de escolaridade")
        private String escolaridade;

        @NotEmptyFile
        @FileExtension(allowedExtensions = {".pdf", ".doc", ".docx"})
        private MultipartFile multipartFile;

        private String observacoes;

}
