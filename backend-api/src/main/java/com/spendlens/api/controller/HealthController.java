package com.spendlens.api.controller;

import com.spendlens.api.constant.AppMessages;
import com.spendlens.api.constant.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(SecurityConstants.HEALTH_ENDPOINT)
    public String healthCheck() {
        return AppMessages.HEALTH_CHECK_SUCCESS;
    }
}
