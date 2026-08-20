package com.example.talimet.teacherEnrollment.service.impl;

import com.example.talimet.common.enums.Role;
import com.example.talimet.common.exception.BadRequestException;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.teacherEnrollment.dto.request.TeacherEnrollmentCreateDto;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;
import com.example.talimet.teacherEnrollment.entity.TeacherEnrollment;
import com.example.talimet.teacherEnrollment.mapper.TeacherEnrollmentMapper;
import com.example.talimet.teacherEnrollment.repository.TeacherEnrollmentRepository;
import com.example.talimet.teacherEnrollment.repository.interfaces.TeacherInfoProjectionByGroup;
import com.example.talimet.teacherEnrollment.service.TeacherEnrollmentService;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherEnrollmentServiceImpl implements TeacherEnrollmentService {
    private final TeacherEnrollmentRepository teacherRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    @Override
    public TeacherEnrollment create(TeacherEnrollmentCreateDto dto) {
        User user = userRepository.findById(dto.teacherId()).orElseThrow(()->new NotFoundException("User not found!"));
        if (user.getRole()!= Role.TEACHER){
            throw new BadRequestException("Dwa");
        }
        Group group = groupRepository.findById(dto.groupId()).orElseThrow(()->new NotFoundException("Group not found!"));
        TeacherEnrollment teacher = TeacherEnrollmentMapper.createDtoToEntity(user,group);
        TeacherEnrollment savedTeacher = teacherRepository.save(teacher);
        return savedTeacher;
    }

    @Override
    public List<TeachersInfo> getTeachersInfoByGroup(UUID groupId) {
        List<TeacherInfoProjectionByGroup> teachers = teacherRepository.getTeachersInfoByGroup(groupId);
        List<TeachersInfo> mappedTeachers = teachers.stream().map(TeacherEnrollmentMapper::groupProjectionToInfo).toList();
        return mappedTeachers;
    }
}
