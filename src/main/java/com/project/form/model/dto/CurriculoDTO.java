package com.project.form.model.dto;

import com.project.form.util.annotation.FileExtension;
import com.project.form.util.annotation.NotEmptyFile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CurriculoDTO {

        @NotBlank(message = "Nome não pode ser vazio")
        private String nome;

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Formato de email inválido")
        private String email;

        @NotBlank(message = "Telefone não pode ser vazio")
        private String telefone;

        @NotBlank(message = "Telefone não pode ser vazio")
        private String cargo;

        @NotBlank(message = "Escolha seu nivel de escolaridade")
        private String escolaridade;

        @NotEmptyFile
        @FileExtension(allowedExtensions = {".pdf", ".doc", ".docx"})
        private MultipartFile multipartFile;

        private String observacoes;

}
