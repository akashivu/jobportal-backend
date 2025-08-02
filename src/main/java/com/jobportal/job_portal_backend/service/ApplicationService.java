package com.jobportal.job_portal_backend.Service;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Entity.JobApplication;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Repository.JobApplicationRepository;
import com.jobportal.job_portal_backend.Repository.JobRepository;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private FileStorageService fileStorageService;

    public String applyToJob(Long jobId, @RequestPart("resume") MultipartFile resume, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        boolean alreadyApplied = jobApplicationRepository.existsByUserAndJob(user, job);
        if (alreadyApplied) {
            return "You have already applied to this job.";
        }
        String fileName = fileStorageService.storeFile(resume);
        JobApplication app = new JobApplication();
        app.setUser(user);
        app.setJob(job);
        app.setAppliedAt(LocalDate.now());
        app.setResumeFileName(fileName);

        jobApplicationRepository.save(app);
        return "Application submitted successfully.";
    }

    public List<JobApplication> getApplicationByJob(Long jobId) {
        List<JobApplication> applications = jobApplicationRepository.findByJobId(jobId);
        if (applications.isEmpty()) {
            throw new RuntimeException("No applications found for job ID: " + jobId);
        }
        return applications;
    }

    public List<JobApplication> getApplicationByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return jobApplicationRepository.findByUser(user);
    }
}
