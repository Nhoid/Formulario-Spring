package com.project.form.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;
import java.lang.reflect.Field;


// VERIFICA SE CANDIDATO ENVIOU ALGUM ARQUIVO
@Documented
@Constraint(validatedBy = {NotEmptyFileValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface NotEmptyFile {

    String message() default "Selecione um arquivo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
