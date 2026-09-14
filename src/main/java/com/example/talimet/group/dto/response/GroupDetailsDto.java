package com.example.talimet.group.dto.response;

import com.example.talimet.lesson.dto.response.GroupLessonBodyResponse;
import com.example.talimet.lessonDays.entity.LessonDays;
import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;

import java.util.List;

public record GroupDetailsDto(
        String groupName,
        String description,
        String lessonTime,
        List<LessonDays> lessonDays,
        List<TeachersInfo> teachers,
        List<StudentsGroupInfoDto> students,
        List<GroupLessonBodyResponse> lessons
) {
}
