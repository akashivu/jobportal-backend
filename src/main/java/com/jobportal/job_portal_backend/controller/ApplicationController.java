package com.jobportal.job_portal_backend.Controller;

import com.jobportal.job_portal_backend.Entity.JobApplication;
import com.jobportal.job_portal_backend.Service.ApplicationService;
import com.jobportal.job_portal_backend.Service.FileStorageService;
import com.jobportal.job_portal_backend.Service.JobService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ApplicationController {
    @Autowired
      private JobService jobService;
     @Autowired
      private ApplicationService applicationService;
     @Autowired
     private FileStorageService fileStorageService;
    @PostMapping("/{jobId}")
    public ResponseEntity<String> applyToJob(@PathVariable Long jobId, @RequestPart("resume") MultipartFile resume, Authentication authentication){
        String email = authentication.getName();
        String res= applicationService.applyToJob(jobId,resume,email);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/my")
    public ResponseEntity<List<JobApplication>> getApplicationByUser(Authentication authentication){
        String email=authentication.getName();
        return ResponseEntity.ok(applicationService.getApplicationByUser(email));
    }
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getApplicationByJobId(@PathVariable Long jobId) {
        List<JobApplication> applications = applicationService.getApplicationByJob(jobId);
        return ResponseEntity.ok(applications);
    }
    @GetMapping("/resume/{fileName}")
    public ResponseEntity<Resource> downloadResume(@PathVariable String fileName) {
        Resource resource = fileStorageService.loadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}
