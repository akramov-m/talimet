package com.example.talimet.student.dto.response;

import java.util.UUID;

public record StudentsGroupInfoDto(
        UUID studentId,
        String studentFullName,
        String studentAverageAttendance
) {
}
