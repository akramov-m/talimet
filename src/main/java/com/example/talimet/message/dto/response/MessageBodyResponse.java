package com.example.talimet.message.dto.response;

import java.util.UUID;

public record MessageBodyResponse(
        UUID questionId,
        String fullName,
        String subject,
        String message,
        String phoneNumber
) {
}
