package com.jobportal.job_portal_backend.Controller;

import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }
  public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("user deleted succesfully");
  }

}
