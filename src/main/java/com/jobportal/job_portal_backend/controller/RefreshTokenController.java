package com.jobportal.job_portal_backend.Controller;
import com.jobportal.job_portal_backend.Dto.AuthResponse;
import com.jobportal.job_portal_backend.Dto.TokenRefreshRequest;
import com.jobportal.job_portal_backend.Entity.RefreshToken;
import com.jobportal.job_portal_backend.Service.JwtService;
import com.jobportal.job_portal_backend.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class RefreshTokenController {
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user.getEmail());
                    return ResponseEntity.ok(new AuthResponse(token, requestToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));
    }
}

