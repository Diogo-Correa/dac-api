package com.dac.api.app.model.edition;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.model.event.Event;
import com.dac.api.app.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "editions")
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private int editionNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private String city;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    @OneToMany(mappedBy = "edition")
    private List<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

}
