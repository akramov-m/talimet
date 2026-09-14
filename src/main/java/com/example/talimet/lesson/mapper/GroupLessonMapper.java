package com.example.talimet.lesson.mapper;

import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;
import com.example.talimet.group.entity.Group;
import com.example.talimet.lesson.dto.request.GroupLessonCreateDto;
import com.example.talimet.lesson.dto.response.GroupLessonBodyResponse;
import com.example.talimet.lesson.dto.response.GroupLessonCreateResponseDto;
import com.example.talimet.lesson.dto.response.GroupLessonDetails;
import com.example.talimet.lesson.entity.Lesson;

import java.util.List;

public class GroupLessonMapper {
    public static Lesson dtoToEntity(GroupLessonCreateDto dto, Group group){
        Lesson lesson = new Lesson();
        lesson.setTitle(dto.title());
        lesson.setGroup(group);
        lesson.setDescription(dto.description());
        lesson.setLessonDate(dto.lessonDate());
        return lesson;
    }

    public static GroupLessonCreateResponseDto entitytoCreateDto(Lesson lesson){
        return new GroupLessonCreateResponseDto(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getGroup().getName(),
                lesson.getLessonDate()

        );
    }

    public static GroupLessonBodyResponse entityToDto(Lesson lesson){
        return new GroupLessonBodyResponse(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getGroup().getName(),
                lesson.getLessonDate()

        );
    }

    public static GroupLessonDetails detailsToDto(List<StudentAttendanceResponse> attendance,Lesson lesson){
        return new GroupLessonDetails(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getLessonDate(),
                attendance
        );
    }
}
