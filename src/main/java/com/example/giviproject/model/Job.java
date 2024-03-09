package com.example.giviproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int salary;

    @ManyToOne
    private Company company;
}
