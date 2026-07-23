package com.example.talimet.auth.dto.login.request;

public record UserLoginRequest(
        String phoneNumber,
        String password
) {
}
