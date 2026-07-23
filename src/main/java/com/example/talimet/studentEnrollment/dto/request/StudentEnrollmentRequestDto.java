package com.example.talimet.studentEnrollment.dto.request;

import java.util.UUID;

public record StudentEnrollmentRequestDto(
        UUID userId,
        UUID groupId
) {
}
