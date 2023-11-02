package com.dac.api.app.controller.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.service.edition.EditionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Editions endpoints")
@RestController
@RequestMapping("/api/editions")
public class EditionController implements Controller<Edition> {
    private final EditionService editionService;

    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @GetMapping()
    public List<Edition> index() {
        return this.editionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Edition> show(@PathVariable Long id) {
        return this.editionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.editionService.deleteById(id);
    }

    @Override
    public Edition create(Edition entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Edition update(Long id, Edition entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
