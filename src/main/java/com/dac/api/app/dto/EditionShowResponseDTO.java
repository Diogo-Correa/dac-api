package com.dac.api.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditionShowResponseDTO {
    private Long id;
    private int editionNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    private int year;

    private List<ActivityResponseDTO> activities;

    private UserResponseDTO organizer;
    private EventResponseDTO event;
}
