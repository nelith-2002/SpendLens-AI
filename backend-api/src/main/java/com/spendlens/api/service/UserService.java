package com.spendlens.api.service;

import com.spendlens.api.constant.AppMessages;
import com.spendlens.api.dto.user.UserProfileResponse;
import com.spendlens.api.entity.AppUser;
import com.spendlens.api.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AppUserRepository appUserRepository;

    public UserProfileResponse getCurrentUser(String email) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        AppMessages.USER_NOT_FOUND
                ));

        return UserProfileResponse.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }
}
