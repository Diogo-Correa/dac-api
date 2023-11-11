package com.dac.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSaveDTO {
    private String username;
    private String email;
    private String password;
}
