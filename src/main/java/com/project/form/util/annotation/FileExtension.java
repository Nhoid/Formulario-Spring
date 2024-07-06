package com.project.form.util.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

// ANNOTATION PERSONALIZADA PARA VERIFICACAO DE TIPO DE ARUIVO
@Documented
@Constraint(validatedBy = {FileExtensionValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface FileExtension {

    String message() default "Tipo de Arquivo inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] allowedExtensions();

}
