package com.dac.api.app.controller.space;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dac.api.app.dto.ApiResponseDTO;
import com.dac.api.app.dto.SpaceResponseDTO;
import com.dac.api.app.dto.SpaceSaveDTO;
import com.dac.api.app.dto.SpaceShowResponseDTO;
import com.dac.api.app.model.space.Space;
import com.dac.api.app.service.space.SpaceService;
import com.dac.api.app.util.GenericMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Space endpoints")
@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private GenericMapper genericMapper;

    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO> index() {
        try {
            List<Space> spaces = this.spaceService.findAll();
            List<SpaceResponseDTO> response = spaces.stream()
                    .map(user -> this.genericMapper.toDTO(user, SpaceResponseDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponseDTO("List of spaces", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> show(@PathVariable Long id) {
        try {
            Optional<Space> space = this.spaceService.findById(id);
            SpaceShowResponseDTO response = this.genericMapper.toDTO(space, SpaceShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Show space", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}/edition/{editionId}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Long id, @PathVariable Long editionId) {
        try {
            this.spaceService.deleteById(id, editionId);
            return ResponseEntity.ok(new ApiResponseDTO("Space deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/{editionId}")
    public ResponseEntity<ApiResponseDTO> create(@PathVariable Long editionId,
            @Valid @RequestBody SpaceSaveDTO entity) {
        try {
            Space space = this.spaceService.save(editionId, entity);
            SpaceShowResponseDTO response = this.genericMapper.toDTO(space, SpaceShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Space created", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}/edition/{editionId}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @Valid @PathVariable Long editionId,
            @Valid @RequestBody SpaceSaveDTO entity) {
        try {
            Space space = this.spaceService.update(id, editionId, entity);
            SpaceShowResponseDTO response = this.genericMapper.toDTO(space, SpaceShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Space updated", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }
}
