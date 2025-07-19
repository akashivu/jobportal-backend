package com.jobportal.job_portal_backend.Controller;

import com.jobportal.job_portal_backend.Dto.JobDto;
import com.jobportal.job_portal_backend.Service.JobService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @PostMapping("/post")
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

}
