package com.project.form.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// REBECER DADOS DE LOGIN
@Data
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
