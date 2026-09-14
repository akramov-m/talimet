package com.example.talimet.attendance.dto.request;

import com.example.talimet.common.enums.AttendanceEnum;

import java.util.UUID;

public record StudentAttendanceCreate(
        UUID studentId,
        AttendanceEnum status
) {
}
