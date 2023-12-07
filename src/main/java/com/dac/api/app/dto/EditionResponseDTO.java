package com.dac.api.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditionResponseDTO {
    private Long id;
    private int edition_number;
    private LocalDate start_date;
    private LocalDate end_date;
    private int year;
}
