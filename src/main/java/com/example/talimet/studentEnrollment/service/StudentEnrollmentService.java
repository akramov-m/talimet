package com.example.talimet.studentEnrollment.service;

import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.studentEnrollment.dto.request.StudentEnrollmentRequestDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.repository.interfaces.StudentProjection;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface StudentEnrollmentService {
    StudentEnrollment create(StudentEnrollmentRequestDto dto);
    List<User> getStudentsByGroup(UUID groupId);
    StudentEnrollment getStudentByIdAndGroup(UUID studentId,UUID groupId);
    Long countOfStudents();
    List<User> getStudentsByBranch(UUID branchId);
    List<StudentsGroupInfoDto> getStudentsInfoByGroup(UUID groupId);
}
