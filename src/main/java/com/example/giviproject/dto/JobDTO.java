package com.example.giviproject.dto;

import com.example.giviproject.model.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobDTO {
    private String title;
    private int salary;
    private Company company;
}
