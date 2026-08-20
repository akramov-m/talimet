package com.example.talimet.student.mapper;

import com.example.talimet.student.dto.response.StudentResponseDto;
import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.student.dto.response.StudentsResponseDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.repository.interfaces.StudentProjection;
import com.example.talimet.user.entity.User;

public class StudentMapper {
    public static StudentResponseDto entityToStudentDto(StudentEnrollment student){
        return new StudentResponseDto(
              student.getStudent().getId(),
              student.getStudent().getFirstName() + " " + student.getStudent().getLastName(),
              student.getStudent().getUsername(),
              student.getStudent().getPhoneNumber()
        );
    }

    public static StudentsResponseDto entityToGroupStudentDto(User entity){
        return new StudentsResponseDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getPhoneNumber()
        );
    }

    public static StudentsGroupInfoDto projectionToDto(StudentProjection projection){
        return new StudentsGroupInfoDto(
                projection.getStudentId(),
                projection.getStudentFirstName()+ " " +projection.getStudentLastName(),
                "100%"
        );
    }


}
