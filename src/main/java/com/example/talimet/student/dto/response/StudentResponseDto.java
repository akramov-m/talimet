package com.example.talimet.student.dto.response;

import java.util.UUID;

public record StudentResponseDto(
        UUID id,
        String fullName,
        String username,
        String phoneNumber
) {
}
