package com.project.form.errorhandler;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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

}
