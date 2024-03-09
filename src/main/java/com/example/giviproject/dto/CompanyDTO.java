package com.example.giviproject.dto;

import com.example.giviproject.model.Job;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyDTO {
    private String companyName;
    private String companyDescription;
    private List<Job> jobDTOList;
}
