package com.example.review.service;

import com.example.review.entity.CountryReview;
import com.example.review.repository.CountryReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryReviewService {
    private final CountryReviewRepository repository;

    public CountryReviewService(CountryReviewRepository repository) {
        this.repository = repository;
    }

    public CountryReview createReview(CountryReview review) {
        return repository.save(review);
    }

    public List<CountryReview> getReviewsByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public List<CountryReview> getReviewsByCountry(String countryId) {
        return repository.findByCountryId(countryId);
    }

    public boolean hasUserReviewedCountry(String userId, String countryId) {
        return repository.existsByUserIdAndCountryId(userId, countryId);
    }

    public List<CountryReview> getCountryReviewsInRange(
            String countryId, Double minRating, Double maxRating) {
        return repository.findByCountryIdAndRatingBetween(countryId, minRating, maxRating);
    }

    public long countReviewsForCountry(String countryId) {
        return repository.countByCountryId(countryId);
    }

    public Optional<CountryReview> getReviewById(Long id) {
        return repository.findById(id);
    }

    public CountryReview updateReview(Long id, CountryReview reviewDetails) {
        CountryReview review = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setReviewText(reviewDetails.getReviewText());
        review.setRating(reviewDetails.getRating());

        return repository.save(review);
    }

    public void deleteReview(Long id) {
        repository.deleteById(id);
    }
}