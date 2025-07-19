package com.jobportal.job_portal_backend.Controller;

import com.jobportal.job_portal_backend.Dto.JobDto;
import com.jobportal.job_portal_backend.Service.JobService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @PostMapping("/post")
    @PreAuthorize("hasRole('RECRUITER')")
    public JobDto postJob(@Valid @RequestBody JobDto jobDto, @RequestParam Long userId) {
        return jobService.createJob(jobDto, userId);
    }


    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }
    @GetMapping("/search")
    public List<JobDto> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location) {
        return jobService.searchJobs(title, location);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{jobId}/apply")
    public ResponseEntity<String> applyToJob(
            @PathVariable Long jobId,
            @RequestParam Long userId) {
        jobService.applyToJob(jobId, userId);
        return ResponseEntity.ok("Applied to job successfully!");
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/applied-jobs/{userId}")
    public ResponseEntity<List<JobDto>> getJobsAppliedByUser(@PathVariable Long userId) {
        List<JobDto> appliedJobs = jobService.getJobsAppliedByUser(userId);
        return ResponseEntity.ok(appliedJobs);
    }


}
