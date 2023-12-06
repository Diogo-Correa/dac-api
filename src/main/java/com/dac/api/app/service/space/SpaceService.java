package com.dac.api.app.service.space;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dac.api.app.dto.SpaceSaveDTO;
import com.dac.api.app.exception.EditionNotFoundException;
import com.dac.api.app.exception.SpaceNotFoundException;
import com.dac.api.app.exception.UserNotOrganizerException;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.model.space.Space;
import com.dac.api.app.repository.edition.EditionRepository;
import com.dac.api.app.repository.space.SpaceRepository;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private GenericMapper genericMapper;

    public List<Space> findAll() {
        return this.spaceRepository.findAll();
    }

    public Space save(Long id, SpaceSaveDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        if (authUser != edition.getOrganizer().getId())
            throw new UserNotOrganizerException();

        Space space = genericMapper.toEntity(data, Space.class);

        return this.spaceRepository.save(space);
    }

    public Optional<Space> findById(Long id) {
        Space space = this.spaceRepository.findById(id).orElseThrow(
                () -> {
                    throw new SpaceNotFoundException();
                });
        return Optional.of(space);
    }

    public Space update(Long id, Long editionId, SpaceSaveDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        if (authUser != edition.getOrganizer().getId())
            throw new UserNotOrganizerException();

        Space space = this.spaceRepository.findById(id).orElseThrow(
                () -> {
                    throw new SpaceNotFoundException();
                });

        if (Integer.valueOf(data.getCapacity()) != null)
            space.setCapacity(data.getCapacity());
        if (data.getName() != null)
            space.setName(data.getName());
        if (data.getLocation() != null)
            space.setLocation(data.getLocation());
        if (data.getResources() != null)
            space.setResources(data.getResources());

        return this.spaceRepository.save(space);
    }

    public void deleteById(Long id, Long editionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        if (authUser != edition.getOrganizer().getId())
            throw new UserNotOrganizerException();

        this.spaceRepository.findById(id).orElseThrow(
                () -> {
                    throw new SpaceNotFoundException();
                });

        this.spaceRepository.deleteById(id);
    }

}
