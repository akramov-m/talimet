package com.example.talimet.user.dto.request;

import com.example.talimet.common.enums.AccountStatus;

import java.util.UUID;

public record UserChangeStatusRequest(
        UUID userId,
        AccountStatus status
) {
}
