package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.ReviewDTO;
import com.example.giviproject.model.Review;
import com.example.giviproject.repository.ReviewRepository;
import com.example.giviproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void createReview(ReviewDTO reviewDTO) {
        Review review = Review
                .builder()
                .title(reviewDTO.getTitle())
                .description(reviewDTO.getDescription())
                .rating(reviewDTO.getRating())
                .company(reviewDTO.getCompany())
                .build();

        reviewRepository.save(review);
    }

    @Override
    public ReviewDTO getReview(long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);

        if(review.isEmpty())
        {
            //custom exception needed
            throw new NullPointerException();
        }

        return ReviewDTO
                .builder()
                .title(review.get().getTitle())
                .description(review.get().getDescription())
                .title(review.get().getTitle())
                .company(review.get().getCompany())
                .build();
    }

    @Override
    public List<ReviewDTO> getAllReviews() {

        List<Review> reviews = (List<Review>) reviewRepository.findAll();
        List<ReviewDTO> reviewDTOS = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDTO reviewDTO = ReviewDTO
                    .builder()
                    .title(review.getTitle())
                    .description(review.getDescription())
                    .rating(review.getRating())
                    .build();

            reviewDTOS.add(reviewDTO);
        }

        return reviewDTOS;
    }
}
