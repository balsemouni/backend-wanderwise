package com.example.country_managment.country.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String name;
    private String imagepath;
    private String description;

    @Enumerated(EnumType.STRING)
    private Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Activities> activities;

    public Country() {
    }

    public Country(String name, String imagepath, String description, Continent continent) {
        this.name = name;
        this.imagepath = imagepath;
        this.description = description;
        this.continent = continent;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public List<Activities> getActivities() {
        return activities;
    }

    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }
}