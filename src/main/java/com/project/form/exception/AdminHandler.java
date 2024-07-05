package com.project.form.exception;

import com.project.form.storage.exception.StorageFileNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class AdminHandler {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public String notFound (StorageFileNotFoundException ex, Model model) {
//
//
//
//    }




}
