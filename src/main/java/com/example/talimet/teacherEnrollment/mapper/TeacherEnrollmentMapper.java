package com.example.talimet.teacherEnrollment.mapper;

import com.example.talimet.group.entity.Group;
import com.example.talimet.teacherEnrollment.dto.response.TeacherEnrollmentCreateResponseDto;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;
import com.example.talimet.teacherEnrollment.entity.TeacherEnrollment;
import com.example.talimet.teacherEnrollment.repository.interfaces.TeacherInfoProjectionByGroup;
import com.example.talimet.user.entity.User;

public class TeacherEnrollmentMapper {
    public static TeacherEnrollment createDtoToEntity(User user, Group group){
        TeacherEnrollment teacher = new TeacherEnrollment();
        teacher.setTeacher(user);
        teacher.setGroup(group);
        return teacher;
    }

    public static TeacherEnrollmentCreateResponseDto entityToCreateDto(String message,TeacherEnrollment teacher){
        return new TeacherEnrollmentCreateResponseDto(
                message + " " +"To" + " " +teacher.getGroup().getName(),
                teacher.getTeacher()
        );
    }

    public static TeachersInfo groupProjectionToInfo(TeacherInfoProjectionByGroup projection){
        return new TeachersInfo(
                projection.getTeacherId(),
                projection.getTeacherFirstName() +" " + projection.getTeacherLastName()
        );
    }
}
