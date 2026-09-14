package com.example.talimet.lesson.dto.request;

public record GroupLessonCreateDto(
        String title,
        String description,
        String lessonDate
) {
}
