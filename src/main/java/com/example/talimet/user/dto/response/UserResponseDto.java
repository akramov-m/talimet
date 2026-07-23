package com.example.talimet.user.dto.response;

public record UserResponseDto(
        String id,
        String firstName,
        String lastName,
        String username,
        String phoneNumber,
        String password,
        String role
) {
}
