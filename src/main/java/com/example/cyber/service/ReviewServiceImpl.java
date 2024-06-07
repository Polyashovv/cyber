package com.example.cyber.service;

import com.example.cyber.model.Review;
import com.example.cyber.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        review.setCreatedAt(new Date());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<Review> getAllReviewsSortedByRating(boolean ascending) {
        return ascending ?
                reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "rating")) :
                reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }

    @Override
    public List<Review> getAllReviewsSortedByDate(boolean ascending) {
        return ascending ?
                reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt")) :
                reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
