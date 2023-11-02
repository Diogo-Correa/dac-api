package com.dac.api.app.controller.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.service.activity.ActivityService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Activity endpoints")
@RestController
@RequestMapping("/api/activities")
public class ActivityController implements Controller<Activity> {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

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

    @Override
    public Activity create(Activity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Activity update(Long id, Activity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
