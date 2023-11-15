package com.dac.api.app.controller.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.EditionSaveDTO;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.service.edition.EditionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Editions endpoints")
@RestController
@RequestMapping("/api/editions")
public class EditionController implements Controller<Edition, EditionSaveDTO> {

    @Autowired
    private EditionService editionService;

    @GetMapping("/")
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

    @PostMapping("/")
    public Edition create(@RequestBody EditionSaveDTO entity) {
        return this.editionService.save(entity);
    }

    @PutMapping("/{id}")
    public Edition update(@PathVariable Long id, @RequestBody EditionSaveDTO entity) {
        return this.editionService.update(id, entity);
    }

    @PatchMapping("/{id}/organizer/{organizer_id}")
    @Operation(description = "Endpoint para adição de organizador.")
    public Edition updateOrganizer(@PathVariable Long id, @PathVariable Long organizer_id) {
        return this.editionService.updateOrganizer(id, organizer_id);
    }

}
