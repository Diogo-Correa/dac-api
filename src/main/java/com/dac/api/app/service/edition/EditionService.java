package com.dac.api.app.service.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.repository.edition.EditionRepository;
import com.dac.api.app.service.Service;

public class EditionService implements Service<Edition> {

    @Autowired
    private EditionRepository editionRepository;

    @Override
    public List<Edition> findAll() {
        return this.editionRepository.findAll();
    }

    @Override
    public Edition save(Edition data) {
        return this.editionRepository.save(data);
    }

    @Override
    public Optional<Edition> findById(Long id) {
        Edition edition = this.editionRepository.getReferenceById(id);
        return Optional.of(edition);
    }

    @Override
    public Edition update(Edition data) {
        return this.editionRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.editionRepository.deleteById(id);
    }

}
