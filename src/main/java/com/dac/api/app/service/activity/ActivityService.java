package com.dac.api.app.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.repository.activity.ActivityRepository;
import com.dac.api.app.service.Service;

@org.springframework.stereotype.Service
public class ActivityService implements Service<Activity> {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }

    @Override
    public Activity save(Activity data) {
        return this.activityRepository.save(data);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        Activity activity = this.activityRepository.getReferenceById(id);
        return Optional.of(activity);
    }

    @Override
    public Activity update(Activity data) {
        return this.activityRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.activityRepository.deleteById(id);
    }

}
