package com.example.talimet.groupLesson.service.impl;

import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.groupLesson.dto.request.GroupLessonCreateDto;
import com.example.talimet.groupLesson.entity.Lesson;
import com.example.talimet.groupLesson.mapper.GroupLessonMapper;
import com.example.talimet.groupLesson.repository.GroupLessonRepository;
import com.example.talimet.groupLesson.service.GroupLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupLessonServiceImpl implements GroupLessonService {
    private final GroupLessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    @Override
    public Lesson createGroupLesson(GroupLessonCreateDto dto) {
        Group group = groupRepository.findById(dto.groupId()).orElseThrow(()->new NotFoundException("Group not found!"));
        Lesson lesson = GroupLessonMapper.dtoToEntity(dto,group);
        Lesson savedLesson = lessonRepository.save(lesson);
        return savedLesson;
    }

    @Override
    public List<Lesson> getLessonsByGroup(UUID groupId) {
        List<Lesson> groupLessons = lessonRepository.getLessonsByGroupId(groupId);
        return groupLessons;
    }
}
