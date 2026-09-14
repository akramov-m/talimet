package com.example.talimet.attendance.dto.response;

import com.example.talimet.common.enums.AttendanceEnum;

import java.util.UUID;

public record StudentAttendanceResponse(
        UUID studentId,
        String fullName,
        AttendanceEnum status
) {
}
