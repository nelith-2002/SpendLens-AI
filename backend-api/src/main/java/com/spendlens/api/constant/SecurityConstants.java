package com.spendlens.api.constant;

public final class SecurityConstants {

    private SecurityConstants() {
        // Prevent object creation
    }

    // Headers and token values
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_TYPE_BEARER = "Bearer";
    public static final String BEARER_PREFIX = TOKEN_TYPE_BEARER + " ";

    // Public endpoints
    public static final String AUTH_API_PREFIX = "/api/auth/";
    public static final String AUTH_API_PATTERN = "/api/auth/**";
    public static final String TEST_ENDPOINT = "/api/test";
    public static final String HEALTH_ENDPOINT = "/api/health";
    public static final String[] PUBLIC_ENDPOINTS = {
            TEST_ENDPOINT,
            HEALTH_ENDPOINT,
            AUTH_API_PATTERN
    };

    // Protected endpoints
    public static final String USERS_API_BASE = "/api/users";
    public static final String CURRENT_USER_PROFILE_ENDPOINT = "/me";
}
