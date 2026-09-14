package com.example.talimet.teacherEnrollment.service;

import com.example.talimet.teacherEnrollment.dto.request.TeacherEnrollmentCreateDto;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;
import com.example.talimet.teacherEnrollment.entity.TeacherEnrollment;
import com.example.talimet.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface TeacherEnrollmentService {
    TeacherEnrollment create(TeacherEnrollmentCreateDto dto);
    List<TeachersInfo> getTeachersInfoByGroup(UUID groupId);
    List<User> getTeachersByBranch(UUID branchId);
}
