package com.spendlens.api.dto.auth;

import com.spendlens.api.constant.AppMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = AppMessages.EMAIL_REQUIRED)
    @Email(message = AppMessages.EMAIL_INVALID)
    private String email;

    @NotBlank(message = AppMessages.PASSWORD_REQUIRED)
    private String password;
}
