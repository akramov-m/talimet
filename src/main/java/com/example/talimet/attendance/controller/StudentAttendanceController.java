package com.example.talimet.attendance.controller;


import com.example.talimet.attendance.dto.request.StudentAttendanceCreate;
import com.example.talimet.attendance.dto.response.StudentAttendanceCreateResponse;
import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;
import com.example.talimet.attendance.service.StudentAttendanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/edu/student-attendance")
@Tag(
        name = "Student Attendance"
)
@RequiredArgsConstructor
public class StudentAttendanceController {
    private final StudentAttendanceService studentAttendanceService;



    @PutMapping("/save")
    public ResponseEntity<StudentAttendanceCreateResponse> saveAttendance(@RequestParam UUID lessonId,
                                                                          @RequestBody List<StudentAttendanceCreate> students){
        StudentAttendanceCreateResponse response = studentAttendanceService.saveAttendance(students,lessonId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping("/{lessonId}/attendance")
    public ResponseEntity<List<StudentAttendanceResponse>> getAttendanceByLesson(@PathVariable UUID lessonId){
        List<StudentAttendanceResponse> attendance = studentAttendanceService.getAttendanceByLessonId(lessonId);
        return ResponseEntity.ok(attendance);
    }
}
