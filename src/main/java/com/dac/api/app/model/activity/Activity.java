package com.dac.api.app.model.activity;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

import com.dac.api.app.model.edition.Edition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import com.dac.api.app.model.user.User;

@Entity
@Data
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private Edition edition;

    @ManyToMany(mappedBy = "favoritedActivities")
    private List<User> favoritedByUsers;
}
