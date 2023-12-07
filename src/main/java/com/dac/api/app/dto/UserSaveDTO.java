package com.dac.api.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
