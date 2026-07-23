package com.example.talimet.group.dto.request;

import java.util.UUID;

public record GroupRequestDto(
        String name,
        UUID subjectId
) {
}
