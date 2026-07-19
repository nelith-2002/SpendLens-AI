package com.spendlens.api.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    private Long userId;
    private String fullName;
    private String email;
}
