package com.dac.api.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
