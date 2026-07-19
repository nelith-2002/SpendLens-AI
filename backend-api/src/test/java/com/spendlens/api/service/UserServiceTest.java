package com.spendlens.api.service;

import com.spendlens.api.constant.AppMessages;
import com.spendlens.api.dto.user.UserProfileResponse;
import com.spendlens.api.entity.AppUser;
import com.spendlens.api.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getCurrentUserReturnsProfileResponse() {
        AppUser user = AppUser.builder()
                .id(1L)
                .fullName("Jane Doe")
                .email("jane@example.com")
                .passwordHash("encoded-password")
                .build();

        when(appUserRepository.findByEmail("jane@example.com")).thenReturn(Optional.of(user));

        UserProfileResponse response = userService.getCurrentUser("jane@example.com");

        assertThat(response.getUserId()).isEqualTo(1L);
        assertThat(response.getFullName()).isEqualTo("Jane Doe");
        assertThat(response.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void getCurrentUserWithUnknownEmailReturnsNotFound() {
        when(appUserRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getCurrentUser("unknown@example.com"))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(exception -> {
                    ResponseStatusException responseException = (ResponseStatusException) exception;
                    assertThat(responseException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(responseException.getReason()).isEqualTo(AppMessages.USER_NOT_FOUND);
                });
    }
}
