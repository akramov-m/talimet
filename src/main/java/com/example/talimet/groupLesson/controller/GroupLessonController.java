package com.example.talimet.groupLesson.controller;


import com.example.talimet.groupLesson.dto.request.GroupLessonCreateDto;
import com.example.talimet.groupLesson.dto.response.GroupLessonBodyResponse;
import com.example.talimet.groupLesson.dto.response.GroupLessonCreateResponseDto;
import com.example.talimet.groupLesson.entity.Lesson;
import com.example.talimet.groupLesson.mapper.GroupLessonMapper;
import com.example.talimet.groupLesson.service.GroupLessonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/group-lesson")
@RequiredArgsConstructor
@Tag(
        name = "Group Lesson"
)
public class GroupLessonController {
    private final GroupLessonService lessonService;


    @PostMapping("/create")
    public ResponseEntity<GroupLessonCreateResponseDto> createGroupLesson(@RequestBody GroupLessonCreateDto dto){
        Lesson lesson = lessonService.createGroupLesson(dto);
        GroupLessonCreateResponseDto response = GroupLessonMapper.entitytoCreateDto(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/lessons")
    public ResponseEntity<List<GroupLessonBodyResponse>> getLessonsByGroup(@RequestParam UUID groupId){
        List<Lesson> lessons = lessonService.getLessonsByGroup(groupId);
        return ResponseEntity.ok(lessons.stream().map(GroupLessonMapper::entityToDto).collect(Collectors.toList()));
    }
}
