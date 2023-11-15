package com.dac.api.app.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.dto.ActivitySaveDTO;
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.repository.activity.ActivityRepository;
import com.dac.api.app.service.Service;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class ActivityService implements Service<Activity, ActivitySaveDTO> {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private GenericMapper genericMapper;

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }

    @Override
    public Activity save(ActivitySaveDTO data) {
        Activity activity = genericMapper.toEntity(data, Activity.class);
        return this.activityRepository.save(activity);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        Activity activity = this.activityRepository.getReferenceById(id);
        return Optional.of(activity);
    }

    @Override
    public Activity update(Long id, ActivitySaveDTO data) {

        Activity activity = this.activityRepository.getReferenceById(id);

        if (activity == null)
            return null;

        return null;

        // return this.activityRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.activityRepository.deleteById(id);
    }

}
