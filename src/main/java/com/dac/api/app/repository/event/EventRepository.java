package com.dac.api.app.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.api.app.model.event.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
