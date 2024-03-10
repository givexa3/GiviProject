package com.example.giviproject.repository;

import com.example.giviproject.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
