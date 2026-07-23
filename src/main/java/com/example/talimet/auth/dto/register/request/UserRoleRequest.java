package com.example.talimet.auth.dto.register.request;

public record UserRoleRequest(
        String userId,
        String role
) {
}
