package com.dac.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.dac.api.app.enums.ActivityType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponseDTO {
    private Long id;
    private ActivityType type;
    private String name;
    private String description;
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time endTime;
}
