package com.spendlens.api.constant;

public final class AppMessages {

    private AppMessages() {
        // Prevent object creation
    }

    // Authentication
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";
    public static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully";
    public static final String LOGIN_SUCCESSFUL = "Login successful";
    public static final String AUTHENTICATION_REQUIRED = "Authentication is required";
    public static final String ACCESS_DENIED = "Access denied";
    public static final String INVALID_OR_EXPIRED_TOKEN = "Invalid or expired token";
    public static final String USER_NOT_FOUND = "User not found";

    // Validation
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String FULL_NAME_REQUIRED = "Full name is required";
    public static final String FULL_NAME_MAX_LENGTH = "Full name must be less than 100 characters";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID = "Email should be valid";
    public static final String EMAIL_MAX_LENGTH = "Email must be less than 150 characters";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_MIN_LENGTH = "Password must be at least 6 characters";

    // System endpoints
    public static final String HEALTH_CHECK_SUCCESS = "SpendLens AI Backend is healthy";
    public static final String TEST_API_SUCCESS = "SpendLens AI Backend is running successfully!";
}
