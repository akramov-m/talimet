package com.example.talimet.branch.dto.request;

import java.util.UUID;

public record BranchRequestDto(
        String name,
        String address,
        UUID eduCenterId
) {
}
