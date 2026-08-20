package com.example.talimet.teacherEnrollment.dto.request;

import java.util.UUID;

public record TeacherEnrollmentCreateDto(
        UUID teacherId,
        UUID groupId
) {
}
