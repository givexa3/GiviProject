package com.example.giviproject.service;

import com.example.giviproject.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewDTO reviewDTO);
    ReviewDTO getReview(long reviewId);
    List<ReviewDTO> getAllReviews();
}
