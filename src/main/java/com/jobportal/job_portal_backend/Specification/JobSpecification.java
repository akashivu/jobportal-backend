package com.jobportal.job_portal_backend.Specification;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Dto.JobSearchRequest;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class JobSpecification {

    public static Specification<Job> getJobs(JobSearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getTitle() != null && !request.getTitle().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + request.getTitle().toLowerCase() + "%"));
            }
            if (request.getLocation() != null && !request.getLocation().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("location")), "%" + request.getLocation().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
