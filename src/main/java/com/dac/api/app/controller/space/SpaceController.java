package com.dac.api.app.controller.space;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.SpaceSaveDTO;
import com.dac.api.app.model.space.Space;
import com.dac.api.app.service.space.SpaceService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Space endpoints")
@RestController
@RequestMapping("/api/spaces")
public class SpaceController implements Controller<Space, SpaceSaveDTO> {

    @Autowired
    private SpaceService spaceService;

    @GetMapping()
    public List<Space> index() {
        return this.spaceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Space> show(@PathVariable Long id) {
        return this.spaceService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.spaceService.deleteById(id);
    }

    @PostMapping("/")
    public Space create(@Valid @RequestBody SpaceSaveDTO entity) {
        return this.spaceService.save(entity);
    }

    @PutMapping("/{id}")
    public Space update(Long id, SpaceSaveDTO entity) {
        return this.spaceService.update(id, entity);
    }
}
