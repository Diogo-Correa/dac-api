package com.dac.api.app.controller.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.service.activity.ActivityService;

public class ActivityController implements Controller<Activity> {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public List<Activity> index() {
        return this.activityService.findAll();
    }

    @Override
    public Optional<Activity> show(@PathVariable Long id) {
        return this.activityService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.activityService.deleteById(id);
    }
}
