package com.jobportal.job_portal_backend.Dto;

import com.jobportal.job_portal_backend.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
    private Role role;
}
