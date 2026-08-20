package com.example.talimet.groupLesson.service;

import com.example.talimet.groupLesson.dto.request.GroupLessonCreateDto;
import com.example.talimet.groupLesson.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface GroupLessonService {
    Lesson createGroupLesson(GroupLessonCreateDto dto);
    List<Lesson> getLessonsByGroup(UUID groupId);
}
