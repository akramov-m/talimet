package com.example.talimet.group.dto.request;

import java.util.UUID;

public record GroupJoinRequestDto(
        UUID userId,
        UUID groupId
) {
}
