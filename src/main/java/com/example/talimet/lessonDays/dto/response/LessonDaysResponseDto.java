package com.example.talimet.lessonDays.dto.response;

import com.example.talimet.common.enums.DaysOfWeek;

public record LessonDaysResponseDto(
        DaysOfWeek dayName
) {
}
