package com.spendlens.api.service;

import com.spendlens.api.constant.AppMessages;
import com.spendlens.api.constant.SecurityConstants;
import com.spendlens.api.dto.auth.AuthResponse;
import com.spendlens.api.dto.auth.LoginRequest;
import com.spendlens.api.dto.auth.RegisterRequest;
import com.spendlens.api.entity.AppUser;
import com.spendlens.api.repository.AppUserRepository;
import com.spendlens.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if (appUserRepository.existsByEmail(normalizedEmail)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, AppMessages.EMAIL_ALREADY_EXISTS);
        }

        AppUser user = AppUser.builder()
                .fullName(request.getFullName().trim())
                .email(normalizedEmail)
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

        AppUser savedUser = appUserRepository.save(user);

        return AuthResponse.builder()
                .userId(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .message(AppMessages.USER_REGISTERED_SUCCESSFULLY)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        AppUser user = appUserRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        AppMessages.INVALID_EMAIL_OR_PASSWORD
                ));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    AppMessages.INVALID_EMAIL_OR_PASSWORD
            );
        }

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .token(token)
                .tokenType(SecurityConstants.TOKEN_TYPE_BEARER)
                .message(AppMessages.LOGIN_SUCCESSFUL)
                .build();
    }
}
