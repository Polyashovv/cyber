package com.example.cyber.repository;

import com.example.cyber.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductId(Long productId);
    @Query("SELECT r FROM Review r ORDER BY r.rating DESC")
    List<Review> findTopReviewsByRating(Pageable pageable);
}
