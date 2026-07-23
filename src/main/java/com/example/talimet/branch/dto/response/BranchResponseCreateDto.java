package com.example.talimet.branch.dto.response;

public record BranchResponseCreateDto(
        String branchId,
        String branchName,
        String message
) {
}
