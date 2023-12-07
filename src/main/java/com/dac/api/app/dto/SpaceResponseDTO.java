package com.dac.api.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceResponseDTO {
    private Long id;
    private int capacity;
    private String name;
    private String location;
    private List<String> resources;
}
