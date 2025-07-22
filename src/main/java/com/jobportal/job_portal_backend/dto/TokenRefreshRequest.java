package com.jobportal.job_portal_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenRefreshRequest {
    private String refreshToken;
}
