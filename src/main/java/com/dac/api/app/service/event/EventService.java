package com.dac.api.app.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.dto.EventSaveDTO;
import com.dac.api.app.exception.EventNotFoundException;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.repository.event.EventRepository;
import com.dac.api.app.service.Service;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class EventService implements Service<Event, EventSaveDTO> {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GenericMapper genericMapper;

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    public Event save(EventSaveDTO dto) {
        Event event = genericMapper.toEntity(dto, Event.class);
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        Event event = this.eventRepository.getReferenceById(id);
        return Optional.of(event);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event update(Long id, EventSaveDTO data) {
        Event event = this.eventRepository.findById(id).orElseThrow(
                () -> {
                    throw new EventNotFoundException();
                });

        event.setName(data.getName());
        event.setDescription(data.getDescription());
        event.setAcronym(data.getAcronym());
        event.setUrl(data.getUrl());
        return this.eventRepository.save(event);
    }

}
