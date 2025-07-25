package com.jobportal.job_portal_backend.Service;

import com.jobportal.job_portal_backend.Dto.AuthRequest;
import com.jobportal.job_portal_backend.Dto.AuthResponse;
import com.jobportal.job_portal_backend.Entity.User;
import com.jobportal.job_portal_backend.Repository.UserRepository;
import com.jobportal.job_portal_backend.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;



    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Set.of(Role.USER))
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return new AuthResponse(token,refreshToken);
    }


    public AuthResponse login(AuthRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getUsername());
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return new AuthResponse(token,refreshToken);
    }
}
