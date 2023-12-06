package com.dac.api.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceSaveDTO {
    private String name;
    private String location;
    private String capacity;
    private List<String> resources;
}
