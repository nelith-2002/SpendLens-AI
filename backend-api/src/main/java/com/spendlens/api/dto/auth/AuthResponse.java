package com.spendlens.api.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private Long userId;
    private String fullName;
    private String email;
    private String message;
}