package com.example.giviproject.controller;

import com.example.giviproject.dto.JobDTO;
import com.example.giviproject.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/job")
    public ResponseEntity<JobDTO> getJob(@RequestParam Long jobId)
    {
        return ResponseEntity.ok(jobService.getJob(jobId));
    }

    @PostMapping("/job")
    public ResponseEntity<HttpStatus> createJob(@RequestBody JobDTO jobDTO)
    {
        //we need to reimplement this looks awful :D
        jobService.createJob(jobDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
