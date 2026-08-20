package com.example.talimet.eduCenter.dto.request;

import java.util.UUID;

public record EduCenterRequestDto(
        String name,
        String address,
        String phoneNumber,
        UUID ownerId
) {
}
