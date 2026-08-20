package com.example.talimet.lessonDays.mapper;

import com.example.talimet.lessonDays.dto.request.LessonDayCreateDto;
import com.example.talimet.lessonDays.dto.response.LessonDaysResponseDto;
import com.example.talimet.lessonDays.entity.LessonDays;

public class LessonDaysMapper {

    public static LessonDaysResponseDto entityToDto(LessonDays entity){
        return new LessonDaysResponseDto(
                entity.getDay()
        );
    }
}
