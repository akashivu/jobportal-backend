package com.jobportal.job_portal_backend.Repository;

import com.jobportal.job_portal_backend.Entity.RefreshToken;
import com.jobportal.job_portal_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);
}

