package com.example.cyber.service;

import com.example.cyber.model.Review;
import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    List<Review> getReviewsByProductId(Long productId);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
    void deleteReview(Long id);
}
