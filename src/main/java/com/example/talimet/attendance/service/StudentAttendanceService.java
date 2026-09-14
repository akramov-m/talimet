package com.example.talimet.attendance.service;

import com.example.talimet.attendance.dto.request.StudentAttendanceCreate;
import com.example.talimet.attendance.dto.response.StudentAttendanceCreateResponse;
import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;
import com.example.talimet.attendance.entity.Attendance;

import java.util.List;
import java.util.UUID;

public interface StudentAttendanceService {

    List<StudentAttendanceResponse> getAttendanceByLessonId(UUID lessonId);
    StudentAttendanceCreateResponse saveAttendance(List<StudentAttendanceCreate> students,UUID lessonId);
}
