package com.spendlens.api.service;

import com.spendlens.api.constant.AppMessages;
import com.spendlens.api.constant.SecurityConstants;
import com.spendlens.api.dto.auth.AuthResponse;
import com.spendlens.api.dto.auth.LoginRequest;
import com.spendlens.api.dto.auth.RegisterRequest;
import com.spendlens.api.entity.AppUser;
import com.spendlens.api.repository.AppUserRepository;
import com.spendlens.api.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void registerReturnsSuccessResponse() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Jane Doe");
        request.setEmail("JANE@example.com");
        request.setPassword("secret123");

        AppUser savedUser = AppUser.builder()
                .id(1L)
                .fullName("Jane Doe")
                .email("jane@example.com")
                .passwordHash("encoded-password")
                .build();

        when(appUserRepository.existsByEmail("jane@example.com")).thenReturn(false);
        when(passwordEncoder.encode("secret123")).thenReturn("encoded-password");
        when(appUserRepository.save(any(AppUser.class))).thenReturn(savedUser);

        AuthResponse response = authService.register(request);

        assertThat(response.getUserId()).isEqualTo(1L);
        assertThat(response.getFullName()).isEqualTo("Jane Doe");
        assertThat(response.getEmail()).isEqualTo("jane@example.com");
        assertThat(response.getMessage()).isEqualTo(AppMessages.USER_REGISTERED_SUCCESSFULLY);
    }

    @Test
    void loginReturnsTokenResponse() {
        LoginRequest request = new LoginRequest();
        request.setEmail("JANE@example.com");
        request.setPassword("secret123");

        AppUser user = AppUser.builder()
                .id(1L)
                .fullName("Jane Doe")
                .email("jane@example.com")
                .passwordHash("encoded-password")
                .build();

        when(appUserRepository.findByEmail("jane@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("secret123", "encoded-password")).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("jwt-token");

        AuthResponse response = authService.login(request);

        assertThat(response.getUserId()).isEqualTo(1L);
        assertThat(response.getToken()).isEqualTo("jwt-token");
        assertThat(response.getTokenType()).isEqualTo(SecurityConstants.TOKEN_TYPE_BEARER);
        assertThat(response.getMessage()).isEqualTo(AppMessages.LOGIN_SUCCESSFUL);
    }

    @Test
    void loginWithWrongPasswordReturnsUnauthorizedError() {
        LoginRequest request = new LoginRequest();
        request.setEmail("jane@example.com");
        request.setPassword("wrong-password");

        AppUser user = AppUser.builder()
                .email("jane@example.com")
                .passwordHash("encoded-password")
                .build();

        when(appUserRepository.findByEmail("jane@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(exception -> {
                    ResponseStatusException responseException = (ResponseStatusException) exception;
                    assertThat(responseException.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
                    assertThat(responseException.getReason()).isEqualTo(AppMessages.INVALID_EMAIL_OR_PASSWORD);
                });
    }

    @Test
    void registerWithExistingEmailReturnsConflictError() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Jane Doe");
        request.setEmail("jane@example.com");
        request.setPassword("secret123");

        when(appUserRepository.existsByEmail("jane@example.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(exception -> {
                    ResponseStatusException responseException = (ResponseStatusException) exception;
                    assertThat(responseException.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
                    assertThat(responseException.getReason()).isEqualTo(AppMessages.EMAIL_ALREADY_EXISTS);
                });
    }

    @Test
    void loginWithUnknownEmailReturnsUnauthorizedError() {
        LoginRequest request = new LoginRequest();
        request.setEmail("unknown@example.com");
        request.setPassword("secret123");

        when(appUserRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(exception -> {
                    ResponseStatusException responseException = (ResponseStatusException) exception;
                    assertThat(responseException.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
                    assertThat(responseException.getReason()).isEqualTo(AppMessages.INVALID_EMAIL_OR_PASSWORD);
                });
    }
}
