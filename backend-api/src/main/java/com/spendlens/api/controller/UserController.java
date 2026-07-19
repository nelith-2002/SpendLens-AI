package com.spendlens.api.controller;

import com.spendlens.api.constant.SecurityConstants;
import com.spendlens.api.dto.user.UserProfileResponse;
import com.spendlens.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecurityConstants.USERS_API_BASE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(SecurityConstants.CURRENT_USER_PROFILE_ENDPOINT)
    public UserProfileResponse getCurrentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication.getName());
    }
}
