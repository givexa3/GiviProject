package com.example.giviproject.dto;

import com.example.giviproject.model.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private String title;
    private String description;
    private double rating;

    //this should be DTO or not? investigate
    //does it really matter if it will be a dto?
    private Company company;
}
