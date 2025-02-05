package com.project.form.exception;


import com.project.form.model.dto.CurriculoDTOInput;
import com.project.form.services.VagaService;
import com.project.form.services.impl.VagaServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.util.logging.Logger;

@ControllerAdvice
public class FormHandler {

    @Autowired
    private VagaService vagaService;

    private Logger logger = Logger.getLogger(FormHandler.class.getName());

    // VALIDACAO DE CAMPOS DO FORMULARIO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidArgument(MethodArgumentNotValidException ex, Model model){

        BindingResult bindingResult = ex.getBindingResult();

        model.addAttribute("org.springframework.validation.BindingResult.curriculo", bindingResult);

        model.addAttribute("vagas", vagaService.findAllAtiva());

        model.addAttribute("curriculo", bindingResult.getTarget());

        return "form";
    }

    // VALIDACAO DO TAMANHO DO ARQUIVO
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public String handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex, Model model) {

        model.addAttribute("curriculo", new CurriculoDTOInput());

        model.addAttribute("sizeError", "Tamanho do arquivo excede limite");

        model.addAttribute("vagas", vagaService.findAll());

        return "form";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex, Model model) {

        model.addAttribute("curriculo", new CurriculoDTOInput());
        model.addAttribute("vagas", vagaService.findAllAtiva());

        logger.info(ex.getRootCause().getMessage());

        if ( ex.getRootCause().getMessage().contains("curriculos.email") ) {
            model.addAttribute("emailWarning", true);
        }

        if ( ex.getRootCause().getMessage().contains("curriculos.telefone") ) {
            model.addAttribute("telefoneWarning", true);
        }


        return "form";
    }



}
