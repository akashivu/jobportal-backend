package com.jobportal.job_portal_backend.Service;

import com.jobportal.job_portal_backend.Dto.UserDto;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Exception.ResourceNotFoundException;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import com.jobportal.job_portal_backend.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }
        User user = User.builder()
                .username(userDto.getName())  // or .username() if that's how it's defined
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return UserDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getUsername())  // again, adjust if using username
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }
    private UserDto convertToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(Role.valueOf(user.getRole().name())); // if role is enum
      
        return dto;
    }


    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }

}
