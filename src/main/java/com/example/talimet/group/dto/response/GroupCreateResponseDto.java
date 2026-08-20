package com.example.talimet.group.dto.response;

import com.example.talimet.lessonDays.dto.response.LessonDaysResponseDto;

import java.util.List;
import java.util.UUID;

public record GroupCreateResponseDto(
        UUID id,
        String name,
        String description,
        String lessonTime,
        List<LessonDaysResponseDto> lessonDays,
        String message
) {
}
