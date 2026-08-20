package com.example.talimet.group.dto.response;

import java.util.UUID;

public record GroupsInfoDtoByBranch(
        UUID groupId,
        String groupName,
        String description,
        String lessonTime,
        String subjectName,
        Long studentCount
) {
}
