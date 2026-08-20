package com.example.talimet.subject.dto.response;

import java.util.UUID;

public record SubjectInfoDto(
        UUID subjectId,
        String subjectName,
        String description,
        Long studentCount,
        Long groupCount
) {
}
