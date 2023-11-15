package com.dac.api.app.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventSaveDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String acronym;
    @NotNull
    private String url;
}
