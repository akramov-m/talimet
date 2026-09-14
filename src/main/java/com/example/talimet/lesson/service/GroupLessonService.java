package com.example.talimet.lesson.service;

import com.example.talimet.lesson.dto.request.GroupLessonCreateDto;
import com.example.talimet.lesson.dto.response.GroupLessonDetails;
import com.example.talimet.lesson.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface GroupLessonService {
    Lesson createGroupLesson(GroupLessonCreateDto dto,UUID groupId);
    Lesson getLessonById(UUID lessonId);
    List<Lesson> getLessonsByGroup(UUID groupId);
    GroupLessonDetails getLessonDetails(UUID lessonId);
}
