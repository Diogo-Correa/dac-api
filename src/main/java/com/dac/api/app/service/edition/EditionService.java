package com.dac.api.app.service.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dac.api.app.dto.EditionSaveDTO;
import com.dac.api.app.enums.UserRole;
import com.dac.api.app.exception.EditionNotFoundException;
import com.dac.api.app.exception.EventNotFoundException;
import com.dac.api.app.exception.UserFoundException;
import com.dac.api.app.exception.UserNotFoundException;
import com.dac.api.app.exception.UserNotOrganizerException;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.model.user.User;
import com.dac.api.app.repository.edition.EditionRepository;
import com.dac.api.app.repository.event.EventRepository;
import com.dac.api.app.repository.user.UserRepository;
import com.dac.api.app.service.Service;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class EditionService implements Service<Edition, EditionSaveDTO> {

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenericMapper genericMapper;

    @Override
    public List<Edition> findAll() {
        return this.editionRepository.findAll();
    }

    @Override
    public Edition save(EditionSaveDTO data) {
        Edition edition = genericMapper.toEntity(data, Edition.class);

        Event event = this.eventRepository.findById(data.getEvent_id()).orElseThrow(
                () -> {
                    throw new EventNotFoundException();
                });

        edition.setEvent(event);

        return editionRepository.save(edition);
    }

    @Override
    public Optional<Edition> findById(Long id) {
        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        return Optional.of(edition);
    }

    @Override
    public Edition update(Long id, EditionSaveDTO data) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        User user = this.userRepository.findById(authUser).orElseThrow(
                () -> {
                    throw new UserNotFoundException();
                });

        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        if (edition.getOrganizer() == null || edition.getOrganizer() != user || user.getRole() != UserRole.ADMIN)
            throw new UserNotOrganizerException();

        this.eventRepository.findById(data.getEvent_id()).orElseThrow(
                () -> {
                    throw new EventNotFoundException();
                });

        return this.editionRepository.save(edition);
    }

    @Override
    public void deleteById(Long id) {
        this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        this.editionRepository.deleteById(id);
    }

    public Edition updateOrganizer(Long id, Long organizer_id) {
        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        User organizer = this.userRepository.findById(organizer_id).orElseThrow(
                () -> {
                    throw new UserFoundException();
                });

        edition.setOrganizer(organizer);

        return this.editionRepository.save(edition);
    }

}
