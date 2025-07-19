package com.jobportal.job_portal_backend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @ManyToMany
    @JoinTable(
            name = "job_applications",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> appliedUsers = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;

    @Column(length = 1000)
    private String description;

    private String salary;

    @ManyToOne
    private User postedBy;
}
