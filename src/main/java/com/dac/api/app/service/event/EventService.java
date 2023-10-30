package com.dac.api.app.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.model.event.Event;
import com.dac.api.app.repository.event.EventRepository;
import com.dac.api.app.service.Service;

public class EventService implements Service<Event> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event save(Event data) {
        return this.eventRepository.save(data);
    }

    @Override
    public Optional<Event> findById(Long id) {
        Event event = this.eventRepository.getReferenceById(id);
        return Optional.of(event);
    }

    @Override
    public Event update(Event data) {
        return this.eventRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }

}
