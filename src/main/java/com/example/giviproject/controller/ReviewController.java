package com.example.giviproject.controller;

import com.example.giviproject.dto.ReviewDTO;
import com.example.giviproject.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//you should write /review here instead of all of the methods below
//define a constant also like ControllerConstants
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable long reviewId)
    {
        //work with ResponseEntities also if else with reponse with different cases
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews()
    {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PostMapping("/review")
    public ResponseEntity<HttpStatus> createReview(@RequestBody ReviewDTO reviewDTO)
    {
        reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
