package com.example.review.repository;

import com.example.review.entity.CountryReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CountryReviewRepository extends JpaRepository<CountryReview, Long> {

    // Basic CRUD operations are inherited from JpaRepository

    /**
     * Find all reviews by a specific user
     * @param userId the user ID to search for
     * @return list of reviews by this user
     */
    List<CountryReview> findByUserId(String userId);

    /**
     * Find all reviews for a specific country
     * @param countryId the country ID to search for
     * @return list of reviews for this country
     */
    List<CountryReview> findByCountryId(String countryId);

    /**
     * Check if a user has already reviewed a specific country
     * @param userId the user ID to check
     * @param countryId the country ID to check
     * @return true if review exists, false otherwise
     */
    boolean existsByUserIdAndCountryId(String userId, String countryId);

    /**
     * Find reviews for a country within a specific rating range
     * @param countryId the country ID to filter by
     * @param minRating minimum rating value (inclusive)
     * @param maxRating maximum rating value (inclusive)
     * @return list of matching reviews
     */
    List<CountryReview> findByCountryIdAndRatingBetween(
            String countryId,
            Double minRating,
            Double maxRating
    );

    /**
     * Find the most recent reviews for a country
     * @param countryId the country ID to filter by
     * @param limit maximum number of reviews to return
     * @return list of recent reviews
     */
    @Query("SELECT cr FROM CountryReview cr " +
            "WHERE cr.countryId = :countryId " +
            "ORDER BY cr.createdAt DESC")
    List<CountryReview> findRecentByCountryId(
            @Param("countryId") String countryId,
            @Param("limit") int limit
    );

    /**
     * Count reviews by country
     * @param countryId the country ID to count for
     * @return number of reviews for this country
     */
    long countByCountryId(String countryId);

    /**
     * Find reviews containing specific text (case-insensitive)
     * @param countryId the country ID to filter by
     * @param searchText text to search for in reviews
     * @return list of matching reviews
     */
    @Query("SELECT cr FROM CountryReview cr " +
            "WHERE cr.countryId = :countryId " +
            "AND LOWER(cr.reviewText) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<CountryReview> findByCountryIdAndTextContaining(
            @Param("countryId") String countryId,
            @Param("searchText") String searchText
    );
}