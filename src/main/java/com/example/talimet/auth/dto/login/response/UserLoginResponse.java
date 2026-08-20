package com.example.talimet.auth.dto.login.response;

import com.example.talimet.common.enums.Role;

import java.util.UUID;

public record UserLoginResponse(
        UUID userId,
        String name,
        Role role,
        String message

) {
}
