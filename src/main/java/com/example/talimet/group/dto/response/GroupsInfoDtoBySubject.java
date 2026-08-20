package com.example.talimet.group.dto.response;

import java.util.UUID;

public record GroupsInfoDtoBySubject(
        UUID groupId,
        String groupName,
        String description,
        String lessonTime,
        Long studentCount,
        Long teacherCount
) {
}
