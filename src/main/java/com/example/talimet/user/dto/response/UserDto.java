package com.example.talimet.user.dto.response;

public record UserDto(
        String id,
        String firstName,
        String lastName,
        String username,
        String phoneNumber
) {
}
