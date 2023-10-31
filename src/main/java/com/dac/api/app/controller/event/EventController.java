package com.dac.api.app.controller.event;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.service.event.EventService;

public class EventController implements Controller<Event> {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public List<Event> index() {
        return this.eventService.findAll();
    }

    @Override
    public Optional<Event> show(@PathVariable Long id) {
        return this.eventService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.eventService.deleteById(id);
    }

}
