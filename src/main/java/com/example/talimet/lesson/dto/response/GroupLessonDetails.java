package com.example.talimet.lesson.dto.response;

import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;

import java.util.List;
import java.util.UUID;

public record GroupLessonDetails(
        UUID lessonId,
        String lessonName,
        String lessonDescription,
        String lessonDate,
        List<StudentAttendanceResponse> attendance

) {
}
