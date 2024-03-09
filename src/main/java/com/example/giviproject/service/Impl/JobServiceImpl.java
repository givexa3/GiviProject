package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.JobDTO;
import com.example.giviproject.model.Job;
import com.example.giviproject.repository.JobRepository;
import com.example.giviproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void createJob(JobDTO jobDto) {
        //we need automapper here
        Job job = Job
                .builder()
                .title(jobDto.getTitle())
                .salary(jobDto.getSalary())
                .company(jobDto.getCompany())
                .build();

        jobRepository.save(job);
    }

    @Override
    public JobDTO getJob(long jobId) {
        Optional<Job> job = jobRepository.findById(jobId);

        if(job.isEmpty())
        {
            //we should throw job not found exception
            throw new NullPointerException();
        }

        return JobDTO
                .builder()
                .title(job.get().getTitle())
                .salary(job.get().getSalary())
                .company(job.get().getCompany())
                .build();
    }
}
