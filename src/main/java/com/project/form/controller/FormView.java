package com.project.form.controller;


import com.project.form.model.dto.CurriculoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormView {

    @GetMapping("/form")
    public String form(Model model) {

        model.addAttribute("curriculo", new CurriculoDTO());

        return "form" ;
    }

}
