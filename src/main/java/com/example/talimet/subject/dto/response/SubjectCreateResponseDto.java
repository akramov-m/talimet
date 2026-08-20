package com.example.talimet.subject.dto.response;

import java.util.UUID;

public record SubjectCreateResponseDto(
        UUID id,
        String name,
        String description,
        String message
) {
}
