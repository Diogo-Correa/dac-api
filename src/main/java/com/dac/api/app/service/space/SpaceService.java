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
import com.dac.api.app.service.Service;
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

        Space space = genericMapper.toEntity(data, Space.class);

        Edition edition = this.editionRepository.findById(id).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        if (authUser != edition.getOrganizer().getId())
            throw new UserNotOrganizerException();

        return this.spaceRepository.save(space);
    }

    public Optional<Space> findById(Long id) {
        Space space = this.spaceRepository.getReferenceById(id);
        return Optional.of(space);
    }

    public Space update(Long id, SpaceSaveDTO data) {

        Space activity = this.spaceRepository.getReferenceById(id);

        if (activity == null)
            return null;

        return null;

        // return this.activityRepository.save(data);
    }

    public void deleteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());
        Space space = this.spaceRepository.findById(id).orElseThrow(
                () -> {
                    throw new SpaceNotFoundException();
                });

        Long organizerId = space.getActivity().getEdition().getOrganizer().getId();

        if (authUser != organizerId)
            throw new UserNotOrganizerException();

        this.spaceRepository.deleteById(id);
    }

}
