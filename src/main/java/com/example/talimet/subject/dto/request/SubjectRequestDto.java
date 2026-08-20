package com.example.talimet.subject.dto.request;

import java.util.UUID;

public record SubjectRequestDto(
        String name,
        String description,
        UUID branchId
) {
}
