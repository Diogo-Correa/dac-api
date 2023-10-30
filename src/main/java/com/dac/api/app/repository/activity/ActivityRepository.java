package com.dac.api.app.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.api.app.model.activity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
