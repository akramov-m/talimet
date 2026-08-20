package com.example.talimet.subject.dto.response;

import java.util.UUID;

public record SubjectResponseDto(
        UUID id,
        String name,
        String description,
        String branchName,
        UUID branchId
) {
}
