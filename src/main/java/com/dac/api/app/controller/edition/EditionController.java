package com.dac.api.app.controller.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.service.edition.EditionService;

public class EditionController implements Controller<Edition> {
    private final EditionService editionService;

    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @Override
    public List<Edition> index() {
        return this.editionService.findAll();
    }

    @Override
    public Optional<Edition> show(@PathVariable Long id) {
        return this.editionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.editionService.deleteById(id);
    }

}
