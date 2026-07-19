package com.spendlens.api.controller;

import com.spendlens.api.constant.AppMessages;
import com.spendlens.api.constant.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(SecurityConstants.TEST_ENDPOINT)
    public String testApi() {
        return AppMessages.TEST_API_SUCCESS;
    }
}
