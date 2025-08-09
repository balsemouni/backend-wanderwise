package com.example.country_managment.country.service;


import com.example.country_managment.country.model.Activities;
import com.example.country_managment.country.model.ActivityClass;
import com.example.country_managment.country.model.Country;
import com.example.country_managment.country.repository.ActivitiesRepository;
import com.example.country_managment.country.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesService {
    private final ActivitiesRepository activitiesRepository;
    private final CountryRepository countryRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository,
                             CountryRepository countryRepository) {
        this.activitiesRepository = activitiesRepository;
        this.countryRepository = countryRepository;
    }

    public List<Activities> getAllActivities() {
        return activitiesRepository.findAll();
    }

    public List<Activities> getActivitiesByCountry(Long countryId) {
        return activitiesRepository.findByCountryId(countryId);
    }

    public Activities createActivity(String activity, ActivityClass activityClass, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        Activities newActivity = new Activities(activity, activityClass, country);
        return activitiesRepository.save(newActivity);
    }

    public void deleteActivity(Long id) {
        activitiesRepository.deleteById(id);
    }
}