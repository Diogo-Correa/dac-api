package com.dac.api.app.model.user;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import com.dac.api.app.model.activity.Activity;

@Entity
@Data
@Table(name = "users")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\S+", message = "O campo [username] não pode conter espaços.")
    private String username;

    @Email(message = "O campo [e-mail] deve conter um e-mail válido.")
    private String email;

    @Length(min = 10, max = 100)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_activities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> favoritedActivities;
}
