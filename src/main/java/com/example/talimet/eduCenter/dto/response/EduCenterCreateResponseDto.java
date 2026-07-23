package com.example.talimet.eduCenter.dto.response;

public record EduCenterCreateResponseDto(
        String id,
        String name,
        String address,
        String phoneNumber,
        String message
) {
}
