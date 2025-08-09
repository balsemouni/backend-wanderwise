package com.example.review.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "country_reviews",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "country_id"}))
public class CountryReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "country_id", nullable = false)
    private String countryId;

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    @Column(nullable = false)
    private Double rating;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public CountryReview() {}

    public CountryReview(String userId, String countryId, Double rating) {
        this.userId = userId;
        this.countryId = countryId;
        this.rating = rating;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCountryId() { return countryId; }
    public void setCountryId(String countryId) { this.countryId = countryId; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
