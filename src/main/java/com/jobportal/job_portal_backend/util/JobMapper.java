package com.jobportal.job_portal_backend.Util;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Dto.JobDto;

public class JobMapper {

    public static JobDto toDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .location(job.getLocation())
                .description(job.getDescription())
                .salary(job.getSalary())
                .postedById(job.getPostedBy().getId())
                .build();
    }

    public static Job toEntity(JobDto dto) {
        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setLocation(dto.getLocation());
        job.setDescription(dto.getDescription());
        job.setSalary(dto.getSalary());
        return job;
    }
}