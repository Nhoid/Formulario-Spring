package com.project.form.controller.admin;

import com.project.form.model.dto.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminAuthView {


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


}
