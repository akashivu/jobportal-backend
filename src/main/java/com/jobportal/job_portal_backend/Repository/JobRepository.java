package com.jobportal.job_portal_backend.Repository;

import com.jobportal.job_portal_backend.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    
}
