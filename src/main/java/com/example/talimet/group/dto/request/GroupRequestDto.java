package com.example.talimet.group.dto.request;

import com.example.talimet.lessonDays.dto.request.LessonDayCreateDto;

import java.util.List;
import java.util.UUID;

public record GroupRequestDto(
        String name,
        String description,
        String lessonTime,
        UUID subjectId,
        List<LessonDayCreateDto> lessonDays
) {
}
