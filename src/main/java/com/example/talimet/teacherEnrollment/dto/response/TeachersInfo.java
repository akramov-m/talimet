package com.example.talimet.teacherEnrollment.dto.response;

import java.util.UUID;

public record TeachersInfo(
        UUID teacherId,
        String teacherFullName
) {
}
