package com.jobportal.job_portal_backend.Repository;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Entity.JobApplication;
import com.jobportal.job_portal_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserId(Long userId);
    boolean existsByUserIdAndJobId(Long userId, Long jobId);
    List<JobApplication> findByJob(Job job);
    List<JobApplication> findByJobId(Long jobId);
    List<JobApplication> findByUser(User user);
    boolean existsByUserAndJob(User user, Job job);
}
