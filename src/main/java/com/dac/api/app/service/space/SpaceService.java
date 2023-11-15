package com.dac.api.app.service.space;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.dto.SpaceSaveDTO;
import com.dac.api.app.model.space.Space;
import com.dac.api.app.repository.space.SpaceRepository;
import com.dac.api.app.service.Service;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class SpaceService implements Service<Space, SpaceSaveDTO> {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private GenericMapper genericMapper;

    @Override
    public List<Space> findAll() {
        return this.spaceRepository.findAll();
    }

    @Override
    public Space save(SpaceSaveDTO data) {
        Space space = genericMapper.toEntity(data, Space.class);
        return this.spaceRepository.save(space);
    }

    @Override
    public Optional<Space> findById(Long id) {
        Space space = this.spaceRepository.getReferenceById(id);
        return Optional.of(space);
    }

    @Override
    public Space update(Long id, SpaceSaveDTO data) {

        Space activity = this.spaceRepository.getReferenceById(id);

        if (activity == null)
            return null;

        return null;

        // return this.activityRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.spaceRepository.deleteById(id);
    }

}
