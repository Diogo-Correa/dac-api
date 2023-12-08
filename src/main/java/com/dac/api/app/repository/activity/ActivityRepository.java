package com.dac.api.app.repository.activity;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.api.app.model.activity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @EntityGraph(attributePaths = { "favoritedByUsers", "edition", "space" })
    List<Activity> findByDateAndStartTimeBetween(
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime);

}
