package com.jobportal.job_portal_backend;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Repository.JobRepository;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobPortalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepo, JobRepository jobRepo) {
		return args -> {
			// Create a dummy recruiter
			User recruiter = User.builder()
					.name("John Recruiter")
					.email("john@job.com")
					.password("test123")
					.role("RECRUITER")
					.build();

			userRepo.save(recruiter);

			// Create a job
			Job job = Job.builder()
					.title("Java Developer")
					.company("Google")
					.location("Remote")
					.description("Looking for strong Java backend skills.")
					.salary("25 LPA")
					.postedBy(recruiter)
					.build();

			jobRepo.save(job);
		};
	}
}
