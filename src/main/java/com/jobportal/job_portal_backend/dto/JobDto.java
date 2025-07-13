package com.jobportal.job_portal_backend.Dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDto {

    private Long id;
    private String title;
    private String company;
    private String location;
    private String description;
    private String salary;
    private Long postedById;
}
