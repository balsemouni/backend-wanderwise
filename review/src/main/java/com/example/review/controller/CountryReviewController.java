package com.example.review.controller;

import com.example.review.entity.CountryReview;
import com.example.review.service.CountryReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class CountryReviewController {

    private final CountryReviewService reviewService;

    public CountryReviewController(CountryReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create a new review
    @PostMapping
    public ResponseEntity<CountryReview> createReview(@RequestBody CountryReview review) {
        CountryReview createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(createdReview);
    }

    // Get all reviews by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CountryReview>> getReviewsByUser(
            @PathVariable String userId) {
        List<CountryReview> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    // Get all reviews for a specific country
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CountryReview>> getReviewsByCountry(
            @PathVariable String countryId) {
        List<CountryReview> reviews = reviewService.getReviewsByCountry(countryId);
        return ResponseEntity.ok(reviews);
    }

    // Check if a user has reviewed a country
    @GetMapping("/exists/user/{userId}/country/{countryId}")
    public ResponseEntity<Boolean> hasUserReviewedCountry(
            @PathVariable String userId,
            @PathVariable String countryId) {
        boolean exists = reviewService.hasUserReviewedCountry(userId, countryId);
        return ResponseEntity.ok(exists);
    }

    // Get reviews for a country within rating range
    @GetMapping("/country/{countryId}/filter")
    public ResponseEntity<List<CountryReview>> getReviewsInRange(
            @PathVariable String countryId,
            @RequestParam Double minRating,
            @RequestParam Double maxRating) {
        List<CountryReview> reviews = reviewService.getCountryReviewsInRange(
                countryId, minRating, maxRating);
        return ResponseEntity.ok(reviews);
    }

    // Get review count for a country
    @GetMapping("/country/{countryId}/count")
    public ResponseEntity<Long> getReviewCountForCountry(
            @PathVariable String countryId) {
        long count = reviewService.countReviewsForCountry(countryId);  // Fixed typo here
        return ResponseEntity.ok(count);
    }

    // Get a specific review by ID
    @GetMapping("/{id}")
    public ResponseEntity<CountryReview> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a review
    @PutMapping("/{id}")
    public ResponseEntity<CountryReview> updateReview(
            @PathVariable Long id,
            @RequestBody CountryReview reviewDetails) {
        CountryReview updatedReview = reviewService.updateReview(id, reviewDetails);
        return ResponseEntity.ok(updatedReview);
    }

    // Delete a review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}