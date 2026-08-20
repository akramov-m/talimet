package com.example.talimet.studentEnrollment.mapper;

import com.example.talimet.group.entity.Group;
import com.example.talimet.studentEnrollment.dto.response.StudentEnrollmentCreateResponseDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.user.entity.User;

public class StudentEnrollmentMapper {
    public static StudentEnrollment dtoToEntity(User user, Group group){
        StudentEnrollment student = new StudentEnrollment();
        student.setGroup(group);
        student.setStudent(user);

        return student;
    };

    public static StudentEnrollmentCreateResponseDto entityToCreateDto(String message){
        return new StudentEnrollmentCreateResponseDto(
           message
        );
    }
}
