package com.example.talimet.group.dto.response;

import java.util.UUID;

public record GroupResponseNoDaysDto(
        UUID id,
        String name,
        String description,
        String lessonTime
) {
}
