package com.dac.api.app.model.space;

import java.util.List;

import com.dac.api.app.model.activity.Activity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "spaces")
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int capacity;

    @ElementCollection
    private List<String> resources;

    @OneToOne(mappedBy = "space", cascade = CascadeType.ALL)
    private Activity activity;

}
