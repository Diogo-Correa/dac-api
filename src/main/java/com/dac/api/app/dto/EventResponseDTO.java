package com.dac.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String acronym;
    private String url;
}
