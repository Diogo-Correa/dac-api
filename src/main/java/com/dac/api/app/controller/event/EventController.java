package com.dac.api.app.controller.event;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.EventSaveDTO;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.service.event.EventService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Event endpoints")
@RestController
@RequestMapping("/api/events")
public class EventController implements Controller<Event, EventSaveDTO> {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public List<Event> index() {
        return this.eventService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Event> show(@PathVariable Long id) {
        return this.eventService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.eventService.deleteById(id);
    }

    @PostMapping("/")
    public Event create(@Valid @RequestBody EventSaveDTO entity) {
        return this.eventService.save(entity);
    }

    @PutMapping("/{id}")
    public Event update(Long id, EventSaveDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
