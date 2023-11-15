package com.dac.api.app.service.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.dto.EditionSaveDTO;
import com.dac.api.app.exception.EditionNotFoundException;
import com.dac.api.app.exception.EventNotFoundException;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.repository.edition.EditionRepository;
import com.dac.api.app.repository.event.EventRepository;
import com.dac.api.app.service.Service;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class EditionService implements Service<Edition, EditionSaveDTO> {

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GenericMapper genericMapper;

    @Override
    public List<Edition> findAll() {
        return this.editionRepository.findAll();
    }

    @Override
    public Edition save(EditionSaveDTO data) {
        Edition edition = genericMapper.toEntity(data, Edition.class);

        return editionRepository.save(edition);
    }

    @Override
    public Optional<Edition> findById(Long id) {
        Edition edition = this.editionRepository.getReferenceById(id);
        return Optional.of(edition);
    }

    @Override
    public Edition update(Long id, EditionSaveDTO data) {

        Edition edition = this.editionRepository.getReferenceById(id);

        if (edition == null) {
            throw new EditionNotFoundException();
        }

        Event event = this.eventRepository.getReferenceById(data.getEvent_id());

        if (event == null) {
            throw new EventNotFoundException();
        }

        edition.setCity(data.getCity());
        edition.setEditionNumber(data.getEditionNumber());
        edition.setYear(data.getYear());
        edition.setStartDate(data.getStartDate());
        edition.setEndDate(data.getEndDate());
        edition.setEvent(event);

        return this.editionRepository.save(edition);
    }

    @Override
    public void deleteById(Long id) {
        this.editionRepository.deleteById(id);
    }

    public Edition updateOrganizer(Long id, Long organizer_id) {
        Edition edition = this.editionRepository.getReferenceById(id);

        return this.editionRepository.save(edition);
    }

}
