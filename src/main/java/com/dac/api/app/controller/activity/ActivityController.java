package com.dac.api.app.controller.activity;

import java.util.List;
import java.util.Optional;

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

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.ActivitySaveDTO;
import com.dac.api.app.dto.ApiResponseDTO;
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.service.activity.ActivityService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Activity endpoints")
@RestController
@RequestMapping("/api/activities")
public class ActivityController implements Controller<ActivitySaveDTO> {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO> index() {
        try {
            List<Activity> activities = this.activityService.findAll();
            return ResponseEntity.ok(new ApiResponseDTO("List of activities", activities));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> show(@PathVariable Long id) {
        try {
            Optional<Activity> activity = this.activityService.findById(id);
            return ResponseEntity.ok(new ApiResponseDTO("Show activity", activity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Long id) {
        try {
            this.activityService.deleteById(id);
            return ResponseEntity.ok(new ApiResponseDTO("Activity deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody ActivitySaveDTO entity) {
        try {
            Activity activity = this.activityService.save(entity);
            return ResponseEntity.ok(new ApiResponseDTO("Activity created", activity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ActivitySaveDTO entity) {
        try {
            Activity activity = this.activityService.update(id, entity);
            return ResponseEntity.ok(new ApiResponseDTO("Activity updated", activity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }
}
