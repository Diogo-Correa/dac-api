package com.dac.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShowResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<ActivityResponseDTO> favoritedActivities;
}
