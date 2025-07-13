package com.jobportal.job_portal_backend.Service;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Dto.JobDto;
import com.jobportal.job_portal_backend.Repository.JobRepository;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import com.jobportal.job_portal_backend.Util.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private UserRepository userRepo;

    public JobDto createJob(JobDto dto, Long userId) {
        User recruiter = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Job job = JobMapper.toEntity(dto);
        job.setPostedBy(recruiter);

        Job saved = jobRepo.save(job);
        return JobMapper.toDto(saved);
    }

    public List<JobDto> getAllJobs() {
        return jobRepo.findAll()
                .stream()
                .map(JobMapper::toDto)
                .collect(Collectors.toList());
    }
}
