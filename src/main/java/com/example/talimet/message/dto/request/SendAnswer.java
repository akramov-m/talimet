package com.example.talimet.message.dto.request;

import java.util.UUID;

public record SendAnswer(
        UUID messageId,
        String responderFullName,
        String answer
) {
}
