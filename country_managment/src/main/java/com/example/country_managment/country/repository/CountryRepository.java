package com.example.country_managment.country.repository;

import com.example.country_managment.country.model.Continent;
import com.example.country_managment.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByContinent(Continent continent);
    List<Country> findByNameContainingIgnoreCase(String name);
}