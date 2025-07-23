package com.jobportal.job_portal_backend.Dto;

import lombok.Data;

@Data
public class JobSearchRequest {
    private String title;
    private String location;
    private int page =0;
    private int size=0;
}
