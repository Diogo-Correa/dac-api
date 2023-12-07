package com.dac.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.sql.Time;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMailResponseDTO {
    private Long id;
    private String name;
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time endTime;
    private List<UserResponseDTO> favoritedByUsers;
}
