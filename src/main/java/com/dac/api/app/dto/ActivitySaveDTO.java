package com.dac.api.app.dto;

import java.sql.Time;
import java.time.LocalDate;

import com.dac.api.app.enums.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySaveDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Time startTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Time endTime;

    @NotNull
    private Long edition_id;

    @NotNull
    private Long space_id;
}
