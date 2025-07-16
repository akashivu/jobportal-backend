package com.jobportal.job_portal_backend.Service;

import com.jobportal.job_portal_backend.Dto.UserDto;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserDto registerUser(UserDto userDto) {

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole())
                .build();

        User savedUser = userRepository.save(user);


        return UserDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
}