package com.dac.api.app.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dac.api.app.enums.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivitySaveDTO {
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    private String name;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time endTime;

    private String location;
}
