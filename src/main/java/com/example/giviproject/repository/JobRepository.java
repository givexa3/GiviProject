package com.example.giviproject.repository;

import com.example.giviproject.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
}
