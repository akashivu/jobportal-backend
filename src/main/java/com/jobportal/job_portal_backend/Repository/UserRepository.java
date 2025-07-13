package com.jobportal.job_portal_backend.Repository;

import com.jobportal.job_portal_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);
}
