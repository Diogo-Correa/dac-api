package com.dac.api.app.controller.event;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.ApiResponseDTO;
import com.dac.api.app.dto.EventResponseDTO;
import com.dac.api.app.dto.EventSaveDTO;
import com.dac.api.app.dto.EventShowResponseDTO;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.service.event.EventService;
import com.dac.api.app.util.GenericMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Event endpoints")
@RestController
@RequestMapping("/api/events")
public class EventController implements Controller<EventSaveDTO> {

    @Autowired
    private EventService eventService;

    @Autowired
    private GenericMapper genericMapper;

    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO> index() {
        try {
            List<Event> events = this.eventService.findAll();
            List<EventResponseDTO> response = events.stream()
                    .map(event -> this.genericMapper.toDTO(event, EventResponseDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponseDTO("List of events", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> show(@PathVariable Long id) {
        try {
            Optional<Event> event = this.eventService.findById(id);

            EventShowResponseDTO response = this.genericMapper.toDTO(event, EventShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Show event", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Long id) {
        try {
            this.eventService.deleteById(id);
            return ResponseEntity.ok(new ApiResponseDTO("Event deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody EventSaveDTO entity) {
        try {
            Event event = this.eventService.save(entity);
            EventShowResponseDTO response = this.genericMapper.toDTO(event, EventShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Event created", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EventSaveDTO entity) {
        try {
            Event event = this.eventService.update(id, entity);
            EventShowResponseDTO response = this.genericMapper.toDTO(event, EventShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Event updated", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

}
