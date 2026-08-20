package com.example.talimet.auth.dto.register.request;

public record UserRegisterRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String userName,
        String password,
        String role
) {
}
