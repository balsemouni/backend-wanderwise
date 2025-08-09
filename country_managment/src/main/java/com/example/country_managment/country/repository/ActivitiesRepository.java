package com.example.country_managment.country.repository;

import com.example.country_managment.country.model.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
    List<Activities> findByCountryId(Long countryId);
}