package com.example.talimet.group.mapper;

import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.*;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionBySubject;
import com.example.talimet.lessonDays.dto.response.LessonDaysResponseDto;
import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;

import java.util.List;

public class GroupMapper {
    public static Group dtoToEntity (GroupRequestDto dto, Subject subject){
        Group group = new Group();
        group.setName(dto.name());
        group.setDescription(dto.description());
        group.setLessonTime(dto.lessonTime());
        group.setSubject(subject);
        return group;
    }

    public static GroupCreateResponseDto entityCreateToDto(Group group,List<LessonDaysResponseDto> lessonDays,String message){
        return new GroupCreateResponseDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getLessonTime(),
                lessonDays,
                message
        );
    }

    public static GroupResponseDto entityToDto(Group g){
        return new GroupResponseDto(
                g.getId(),
                g.getName(),
                g.getDescription(),
                g.getLessonTime(),
                g.getLessonDays()
        );
    }

    public static GroupResponseNoDaysDto entityToDtoNoDays(Group group){
        return new GroupResponseNoDaysDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getLessonTime()
        );
    }

    public static GroupsInfoDtoBySubject projectionToDto(GroupsInfoProjectionBySubject projection){
        return new GroupsInfoDtoBySubject(
                projection.getGroupId(),
                projection.getGroupName(),
                projection.getDescription(),
                projection.getLessonTime(),
                projection.getStudentCount(),
                projection.getTeacherCount()
        );
    }

    public static GroupDetailsDto detailsEntitiesToDto(Group group, List<StudentsGroupInfoDto> students, List<TeachersInfo> teachers){
        return new GroupDetailsDto(
                group.getName(),
                group.getDescription(),
                group.getLessonTime(),
                teachers,
                students
        );
    }

    public static GroupsInfoDtoByBranch projectionToDtoByBranch(GroupsInfoProjectionByBranch projection){
        return new GroupsInfoDtoByBranch(
                projection.getGroupId(),
                projection.getGroupName(),
                projection.getDescription(),
                projection.getLessonTime(),
                projection.getSubjectName(),
                projection.getStudentCount()
        );
    }
}
