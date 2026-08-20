package com.example.talimet.lessonDays.service;

import com.example.talimet.group.entity.Group;
import com.example.talimet.lessonDays.dto.request.LessonDayCreateDto;
import com.example.talimet.lessonDays.entity.LessonDays;

import java.util.List;
import java.util.UUID;

public interface LessonDaysService {
    List<LessonDays> create(Group group, List<LessonDayCreateDto> dto);
    List<LessonDays> getLessonDaysByGroup(UUID groupId);
}
