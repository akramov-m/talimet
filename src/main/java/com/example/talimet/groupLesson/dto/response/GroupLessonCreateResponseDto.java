package com.example.talimet.groupLesson.dto.response;

import java.util.UUID;

public record GroupLessonCreateResponseDto(
        UUID lessonId,
        String title,
        String description,
        String groupName,
        String lessonDate
) {
}
