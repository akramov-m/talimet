package com.example.talimet.groupLesson.dto.request;

import java.util.UUID;

public record GroupLessonCreateDto(
        UUID groupId,
        String title,
        String description,
        String lessonDate
) {
}
