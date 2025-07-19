package com.jobportal.job_portal_backend;

import com.jobportal.job_portal_backend.Entity.Job;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Repository.JobRepository;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import com.jobportal.job_portal_backend.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(prePostEnabled = true)
@SpringBootApplication

public class JobPortalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalBackendApplication.class, args);
	}
	@Autowired
   private UserRepository userRepository;
	@Bean
	public CommandLineRunner runner(UserRepository userRepo, JobRepository jobRepo) {
		return args -> {

			if (!userRepo.existsByEmail("john@job.com")) {
				User recruiter = User.builder()
						.username("John Recruiter")
						.email("john@job.com")
						.password("test123")
						.role(Role.RECRUITER)
						.build();

				userRepo.save(recruiter);

				Job job = Job.builder()
						.title("Java Developer")
						.company("Google")
						.location("Remote")
						.description("Looking for strong Java backend skills.")
						.salary("25 LPA")
						.postedBy(recruiter)
						.build();

				jobRepo.save(job);
			}
		};
	}

}
