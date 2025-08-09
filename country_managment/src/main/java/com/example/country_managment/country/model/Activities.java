package com.example.country_managment.country.model;

import jakarta.persistence.*;

@Entity
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_act;

    private String activity;

    @Enumerated(EnumType.STRING)
    private ActivityClass activity_class;

    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;

    // Constructors, getters, and setters
    public Activities() {
    }

    public Activities(String activity, ActivityClass activity_class, Country country) {
        this.activity = activity;
        this.activity_class = activity_class;
        this.country = country;
    }

    // Getters and setters
    public Long getId_act() {
        return id_act;
    }

    public void setId_act(Long id_act) {
        this.id_act = id_act;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public ActivityClass getActivity_class() {
        return activity_class;
    }

    public void setActivity_class(ActivityClass activity_class) {
        this.activity_class = activity_class;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}