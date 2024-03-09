package com.example.giviproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobDTO {
    private String title;
    private int salary;
}
