package com.jobportal.job_portal_backend.Service;
import com.jobportal.job_portal_backend.Dto.JobSearchRequest;
import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Dto.JobDto;
import com.jobportal.job_portal_backend.Exception.ResourceNotFoundException;
import com.jobportal.job_portal_backend.Repository.JobApplicationRepository;
import com.jobportal.job_portal_backend.Repository.JobRepository;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import com.jobportal.job_portal_backend.Specification.JobSpecification;
import com.jobportal.job_portal_backend.Util.JobMapper;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    JobApplicationRepository applicationRepository;
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
    public List<JobDto> searchJobs(String title, String location) {
        List<Job> jobs = jobRepo.searchJobs(title, location);
        return jobs.stream()
                .map(job -> JobDto.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .description(job.getDescription())
                        .location(job.getLocation())
                        .salary(job.getSalary())
                        .company(job.getCompany())
                        .build())
                .toList();
    }
    private JobDto convertToDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .company(job.getCompany())
                .location(job.getLocation())
                .salary(job.getSalary())
                .build();
    }

    public void applyToJob(Long jobId, Long userId) {
        if (applicationRepository.existsByUserIdAndJobId(userId, jobId)) {
            throw new RuntimeException("User already applied to this job");
        }

        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        job.getAppliedUsers().add(user);
        jobRepo.save(job);
    }
    public List<JobDto> getJobsAppliedByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Job> appliedJobs = jobRepo.findAll()
                .stream()
                .filter(job -> job.getAppliedUsers().contains(user))
                .toList();

        return appliedJobs.stream()
                .map(this::convertToDto)
                .toList();
    }
    public Page<Job> searchJobs(JobSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Specification<Job> spec = JobSpecification.getJobs(request);
        return jobRepo.findAll(spec, pageable);
    }



}
