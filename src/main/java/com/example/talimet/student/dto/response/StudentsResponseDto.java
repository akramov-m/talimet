package com.example.talimet.student.dto.response;

import java.util.UUID;

public record StudentsResponseDto(
        UUID id,
        String firstName,
        String lastName,
        String username,
        String phoneNumber
) {
}
