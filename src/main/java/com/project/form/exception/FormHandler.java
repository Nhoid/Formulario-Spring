package com.project.form.exception;


import com.project.form.model.dto.CurriculoDTOInput;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.logging.Logger;

@ControllerAdvice
public class FormHandler {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidArgument(MethodArgumentNotValidException ex, Model model){

        BindingResult bindingResult = ex.getBindingResult();

        model.addAttribute("org.springframework.validation.BindingResult.curriculo", bindingResult);

        model.addAttribute("curriculo", bindingResult.getTarget());

        return "form";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public String handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex, Model model) {

        model.addAttribute("curriculo", new CurriculoDTOInput());

        model.addAttribute("sizeError", "Tamanho do arquivo excede limite");

        return "form";
    }



}
