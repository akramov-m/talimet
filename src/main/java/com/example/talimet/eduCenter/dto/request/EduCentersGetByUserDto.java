package com.example.talimet.eduCenter.dto.request;

import com.example.talimet.common.enums.Role;

import java.util.UUID;

public record EduCentersGetByUserDto(
        UUID userId,
        Role role
) {
}
