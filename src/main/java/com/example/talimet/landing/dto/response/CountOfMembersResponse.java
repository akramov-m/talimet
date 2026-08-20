package com.example.talimet.landing.dto.response;

public record CountOfMembersResponse(
        Long numberOfUsers,
        Long numberOfStudents,
        Long numberOfEduCenters
) {
}
