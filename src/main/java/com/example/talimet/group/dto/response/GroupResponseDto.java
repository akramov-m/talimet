package com.example.talimet.group.dto.response;

import com.example.talimet.lessonDays.dto.response.LessonDaysResponseDto;
import com.example.talimet.lessonDays.entity.LessonDays;
import com.example.talimet.lessonDays.repository.interfaces.LessonDayProjection;

import java.util.List;
import java.util.UUID;

public record GroupResponseDto(
        UUID id,
        String name,
        String description,
        String lessonTime,
        List<LessonDays> days
) {
}
