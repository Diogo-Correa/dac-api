package com.dac.api.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceSaveDTO {

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private int capacity;

    @NotNull
    private List<String> resources;
}
