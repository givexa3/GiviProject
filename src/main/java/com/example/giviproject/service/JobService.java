package com.example.giviproject.service;

import com.example.giviproject.dto.JobDTO;

public interface JobService {
    void createJob(JobDTO jobDto);
    JobDTO getJob(long jobId);
}
