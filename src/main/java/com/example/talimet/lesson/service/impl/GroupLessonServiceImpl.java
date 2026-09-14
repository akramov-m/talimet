package com.example.talimet.lesson.service.impl;

import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;
import com.example.talimet.attendance.service.StudentAttendanceService;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.lesson.dto.request.GroupLessonCreateDto;
import com.example.talimet.lesson.dto.response.GroupLessonDetails;
import com.example.talimet.lesson.entity.Lesson;
import com.example.talimet.lesson.mapper.GroupLessonMapper;
import com.example.talimet.lesson.repository.GroupLessonRepository;
import com.example.talimet.lesson.service.GroupLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupLessonServiceImpl implements GroupLessonService {
    private final GroupLessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final StudentAttendanceService studentAttendanceService;


    @Override
    public Lesson createGroupLesson(GroupLessonCreateDto dto,UUID groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(()->new NotFoundException("Group not found!"));
        Lesson lesson = GroupLessonMapper.dtoToEntity(dto,group);
        Lesson savedLesson = lessonRepository.save(lesson);
        return savedLesson;
    }

    @Override
    public Lesson getLessonById(UUID lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(()->new NotFoundException("Lesson not found!"));

    }

    @Override
    public List<Lesson> getLessonsByGroup(UUID groupId) {
        List<Lesson> groupLessons = lessonRepository.getLessonsByGroupId(groupId);
        return groupLessons;
    }

    @Override
    public GroupLessonDetails getLessonDetails(UUID lessonId) {
        List<StudentAttendanceResponse> attendance = studentAttendanceService.getAttendanceByLessonId(lessonId);
        Lesson lesson = getLessonById(lessonId);
        return GroupLessonMapper.detailsToDto(attendance,lesson);
    }
}
