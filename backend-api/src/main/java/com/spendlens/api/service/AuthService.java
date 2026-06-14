package com.spendlens.api.service;

import com.spendlens.api.dto.auth.AuthResponse;
import com.spendlens.api.dto.auth.RegisterRequest;
import com.spendlens.api.entity.AppUser;
import com.spendlens.api.repository.AppUserRepository;
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

    public AuthResponse register(RegisterRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if (appUserRepository.existsByEmail(normalizedEmail)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
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
                .message("User registered successfully")
                .build();
    }
}