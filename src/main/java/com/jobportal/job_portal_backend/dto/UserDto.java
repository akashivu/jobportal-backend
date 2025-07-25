package com.jobportal.job_portal_backend.Dto;

import com.jobportal.job_portal_backend.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> role;


}
