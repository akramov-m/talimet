package com.example.talimet.auth.dto.register.response;

import java.util.UUID;

public record UserRegisterResponse(
        UUID userId,
        String message
) {
}
