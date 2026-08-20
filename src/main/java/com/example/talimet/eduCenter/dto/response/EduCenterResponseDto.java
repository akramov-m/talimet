package com.example.talimet.eduCenter.dto.response;

import java.time.LocalDateTime;

public record EduCenterResponseDto(
        String id,
        String name,
        String address,
        String phoneNumber,
        String ownerFullName,
        LocalDateTime createdAt
) {
}
