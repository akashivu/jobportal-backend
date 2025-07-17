package com.jobportal.job_portal_backend.Repository;

import com.jobportal.job_portal_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);



    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}

