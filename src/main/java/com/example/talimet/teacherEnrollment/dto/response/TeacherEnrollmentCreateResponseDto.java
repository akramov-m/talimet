package com.example.talimet.teacherEnrollment.dto.response;

import com.example.talimet.user.entity.User;

public record TeacherEnrollmentCreateResponseDto(
        String message,
        User user
) {
}
