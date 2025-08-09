package com.example.country_managment.country.model;


import com.example.country_managment.country.model.Country;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // This could be changed to a proper User entity relationship if needed

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "historique_countries",
            joinColumns = @JoinColumn(name = "historique_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

    // Constructors, getters, and setters
    public Historique() {
    }

    public Historique(Long userId, List<Country> countries) {
        this.userId = userId;
        this.countries = countries;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}