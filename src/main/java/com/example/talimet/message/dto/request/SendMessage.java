package com.example.talimet.message.dto.request;

public record SendMessage(
        String fullName,
        String subject,
        String message,
        String phoneNumber
) {
}
