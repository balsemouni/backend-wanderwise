package com.example.country_managment.country.controller;

import com.example.country_managment.country.model.Activities;
import com.example.country_managment.country.model.ActivityClass;
import com.example.country_managment.country.model.Continent;
import com.example.country_managment.country.service.ActivitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {

    private final ActivitiesService activitiesService;

    public ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @GetMapping
    public ResponseEntity<List<Activities>> getAllActivities() {
        List<Activities> activities = activitiesService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<Activities>> getActivitiesByCountry(@PathVariable Long countryId) {
        List<Activities> activities = activitiesService.getActivitiesByCountry(countryId);
        return ResponseEntity.ok(activities);
    }
    public record ActivityRequest(String activity,ActivityClass activityClass,Long countryId) {}

    @PostMapping
    public ResponseEntity<Activities> createActivity(
            @RequestBody ActivityRequest request) {
        Activities newActivity = activitiesService.createActivity(request.activity, request.activityClass, request.countryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newActivity);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activitiesService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}