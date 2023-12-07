package com.dac.api.app.controller.edition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.dac.api.app.dto.ApiResponseDTO;
import com.dac.api.app.dto.EditionSaveDTO;
import com.dac.api.app.dto.EditionShowResponseDTO;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.service.edition.EditionService;
import com.dac.api.app.util.GenericMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Editions endpoints")
@RestController
@RequestMapping("/api/editions")
public class EditionController implements Controller<EditionSaveDTO> {

    @Autowired
    private EditionService editionService;

    @Autowired
    private GenericMapper genericMapper;

    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO> index() {
        try {
            List<Edition> editions = this.editionService.findAll();
            return ResponseEntity.ok(new ApiResponseDTO("List of editions", editions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> show(@PathVariable Long id) {
        try {
            Optional<Edition> edition = this.editionService.findById(id);
            EditionShowResponseDTO response = this.genericMapper.toDTO(edition, EditionShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Show edition", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Long id) {
        try {
            this.editionService.deleteById(id);
            return ResponseEntity.ok(new ApiResponseDTO("Edition deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody EditionSaveDTO entity) {
        try {
            Edition edition = this.editionService.save(entity);
            EditionShowResponseDTO response = this.genericMapper.toDTO(edition, EditionShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Edition created", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EditionSaveDTO entity) {
        try {
            Edition edition = this.editionService.update(id, entity);
            EditionShowResponseDTO response = this.genericMapper.toDTO(edition, EditionShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Edition updated", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PatchMapping("/{id}/organizer/{organizer_id}")
    @Operation(description = "Endpoint para adição de organizador.")
    public ResponseEntity<ApiResponseDTO> updateOrganizer(@PathVariable Long id, @PathVariable Long organizer_id) {
        try {
            Edition edition = this.editionService.updateOrganizer(id, organizer_id);
            EditionShowResponseDTO response = this.genericMapper.toDTO(edition, EditionShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Edition organizer setted", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

}
