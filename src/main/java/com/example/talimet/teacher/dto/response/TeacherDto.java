package com.example.talimet.teacher.dto.response;

import java.util.UUID;

public record TeacherDto(
        UUID id,
        String fullName,
        String username,
        String phoneNumber
) {
}
