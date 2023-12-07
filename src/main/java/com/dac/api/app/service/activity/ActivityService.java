package com.dac.api.app.service.activity;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dac.api.app.dto.ActivitySaveDTO;
import com.dac.api.app.exception.ActivityNotFoundException;
import com.dac.api.app.exception.EditionNotFoundException;
import com.dac.api.app.exception.SpaceNotFoundException;
import com.dac.api.app.exception.UserNotOrganizerException;
import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.model.space.Space;
import com.dac.api.app.repository.activity.ActivityRepository;
import com.dac.api.app.repository.edition.EditionRepository;
import com.dac.api.app.repository.space.SpaceRepository;
import com.dac.api.app.service.Service;
import com.dac.api.app.util.GenericMapper;

@org.springframework.stereotype.Service
public class ActivityService implements Service<Activity, ActivitySaveDTO> {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private GenericMapper genericMapper;

    @Override
    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }

    @Override
    public Activity save(ActivitySaveDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        Edition edition = this.editionRepository.findById(data.getEdition_id()).orElseThrow(
                () -> {
                    throw new EditionNotFoundException();
                });

        if (authUser != edition.getOrganizer().getId())
            throw new UserNotOrganizerException();

        Space space = this.spaceRepository.findById(data.getSpace_id()).orElseThrow(
                () -> {
                    throw new SpaceNotFoundException();
                });

        Activity activity = genericMapper.toEntity(data, Activity.class);

        activity.setEdition(edition);
        activity.setSpace(space);

        return this.activityRepository.save(activity);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        Activity activity = this.activityRepository.findById(id).orElseThrow(
                () -> {
                    throw new ActivityNotFoundException();
                });
        return Optional.of(activity);
    }

    @Override
    public Activity update(Long id, ActivitySaveDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        Activity activity = this.activityRepository.findById(id).orElseThrow(
                () -> {
                    throw new ActivityNotFoundException();
                });

        if (authUser != activity.getEdition().getOrganizer().getId())
            throw new UserNotOrganizerException();

        return this.activityRepository.save(activity);
    }

    @Override
    public void deleteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authUser = Long.valueOf(authentication.getName());

        Activity activity = this.activityRepository.findById(id).orElseThrow(
                () -> {
                    throw new ActivityNotFoundException();
                });

        if (authUser != activity.getEdition().getOrganizer().getId())
            throw new UserNotOrganizerException();

        this.activityRepository.deleteById(id);
    }

    public List<Activity> getActivitiesStartingWithinNextHour() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime oneHourLater = now.plusHours(1);

        System.out.println("OneHourLater: " + oneHourLater.toLocalTime());

        return activityRepository.findByDateAndStartTimeBetween(now.toLocalDate(),
                now.toLocalTime(), oneHourLater.toLocalTime());
    }

}
