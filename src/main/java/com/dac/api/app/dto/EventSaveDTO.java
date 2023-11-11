package com.dac.api.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventSaveDTO {
    private String name;
    private String description;
    private String acronym;
    private String url;
}
