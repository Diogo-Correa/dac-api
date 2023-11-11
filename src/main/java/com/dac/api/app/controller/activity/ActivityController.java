package com.dac.api.app.controller.activity;

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

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.ActivitySaveDTO;
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.service.activity.ActivityService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Activity endpoints")
@RestController
@RequestMapping("/api/activities")
public class ActivityController implements Controller<Activity, ActivitySaveDTO> {

    @Autowired
    private ActivityService activityService;

    @GetMapping()
    public List<Activity> index() {
        return this.activityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Activity> show(@PathVariable Long id) {
        return this.activityService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.activityService.deleteById(id);
    }

    @PostMapping("/")
    public Activity create(ActivitySaveDTO entity) {
        return this.activityService.save(entity);
    }

    @PutMapping("/{id}")
    public Activity update(Long id, ActivitySaveDTO entity) {
        return this.activityService.update(id, entity);
    }
}
