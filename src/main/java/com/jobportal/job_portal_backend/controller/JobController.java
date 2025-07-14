package com.jobportal.job_portal_backend.Controller;

import com.jobportal.job_portal_backend.Dto.JobDto;
import com.jobportal.job_portal_backend.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @PostMapping
    public JobDto postJob(@RequestBody JobDto jobDto, @RequestParam Long userId) {
        return jobService.createJob(jobDto, userId);
    }


    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }
}
