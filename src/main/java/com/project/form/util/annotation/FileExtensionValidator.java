package com.project.form.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {


    private String[] allowedExtensions;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
        this.allowedExtensions = constraintAnnotation.allowedExtensions();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        String originalName = multipartFile.getOriginalFilename();

        if (originalName == null || originalName.trim().isEmpty() || originalName.isBlank()) {
            return false; // Arquivo sem nome
        }

        for (String extension : allowedExtensions) {
            if (originalName.toLowerCase().endsWith(extension.toLowerCase())) {
                return true; // Extensao de arquivo valida
            }
        }

        return false; // nao encontrou extensao valida de arquivo
    }
}
