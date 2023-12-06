package com.dac.api.app.controller.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.service.activity.ActivityService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Activity endpoints")
@RestController
@RequestMapping("/api/activities")
public class ActivityController implements Controller<Activity, ActivitySaveDTO> {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/")
    public ResponseEntity<List<Activity>> index() {
        return ResponseEntity.ok(this.activityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Activity>> show(@PathVariable Long id) {
        return ResponseEntity.ok(this.activityService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.activityService.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Activity> create(@Valid @RequestBody ActivitySaveDTO entity) {
        return ResponseEntity.ok(this.activityService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> update(@PathVariable Long id, @Valid @RequestBody ActivitySaveDTO entity) {
        return ResponseEntity.ok(this.activityService.update(id, entity));
    }
}
