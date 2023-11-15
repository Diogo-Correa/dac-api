package com.dac.api.app.model.activity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dac.api.app.enums.ActivityType;
import com.dac.api.app.model.edition.Edition;
import com.dac.api.app.model.space.Space;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    private String name;
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time endTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id")
    private Space space;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private Edition edition;

    @ManyToMany(mappedBy = "favoritedActivities")
    private List<User> favoritedByUsers;
}
