package com.example.talimet.teacherEnrollment.controller;


import com.example.talimet.teacherEnrollment.dto.request.TeacherEnrollmentCreateDto;
import com.example.talimet.teacherEnrollment.dto.response.TeacherEnrollmentCreateResponseDto;
import com.example.talimet.teacherEnrollment.entity.TeacherEnrollment;
import com.example.talimet.teacherEnrollment.mapper.TeacherEnrollmentMapper;
import com.example.talimet.teacherEnrollment.service.TeacherEnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher/enrollment")
@Tag(
        name = "Teacher Enrollment"
)
@RequiredArgsConstructor
public class TeacherEnrollmentController {
    private final TeacherEnrollmentService teacherService;


    @PostMapping("/create")
    public ResponseEntity<TeacherEnrollmentCreateResponseDto> create(@RequestBody TeacherEnrollmentCreateDto dto){
        TeacherEnrollment teacher = teacherService.create(dto);
        String message = "Teacher successfully created";
        return ResponseEntity.status(HttpStatus.CREATED).body(TeacherEnrollmentMapper.entityToCreateDto(message,teacher));
    }
}
