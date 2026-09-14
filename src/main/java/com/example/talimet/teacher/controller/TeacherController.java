package com.example.talimet.teacher.controller;


import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.mapper.SubjectMapper;
import com.example.talimet.subject.service.SubjectService;
import com.example.talimet.teacher.dto.response.TeacherDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/edu/teacher")
@RequiredArgsConstructor
@Tag(
        name = "Teacher"
)
public class TeacherController {
    private final SubjectService subjectService;


    @GetMapping("/{branchId}/{teacherId}/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getSubjectsByTeacher(@PathVariable UUID branchId,@PathVariable UUID teacherId) {
        List<Subject> subjects = subjectService.getSubjectsByTeacher(teacherId, branchId);
        return ResponseEntity.ok(subjects.stream().map(SubjectMapper::entityToDto).toList());
    }
}
