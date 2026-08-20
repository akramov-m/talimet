package com.example.talimet.studentEnrollment.service.impl;

import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.student.mapper.StudentMapper;
import com.example.talimet.studentEnrollment.dto.request.StudentEnrollmentRequestDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.mapper.StudentEnrollmentMapper;
import com.example.talimet.studentEnrollment.repository.StudentEnrollmentRepository;
import com.example.talimet.studentEnrollment.repository.interfaces.StudentProjection;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;


    @Override
    public StudentEnrollment create(StudentEnrollmentRequestDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(()->new NotFoundException("User not found!"));
        Group group = groupRepository.findById(dto.groupId())
                .orElseThrow(()->new NotFoundException("Group not found!"));

        StudentEnrollment student = StudentEnrollmentMapper.dtoToEntity(user,group);
        StudentEnrollment savedStudent = studentEnrollmentRepository.save(student);
        return savedStudent;
    }

    @Override
    public List<User> studentEnrollmentsByGroup(UUID groupId) {
        List<User> studentEnrollments = studentEnrollmentRepository.getStudentEnrollmentsByGroup(groupId);
        return studentEnrollments;
    }

    @Override
    public StudentEnrollment getStudentById(UUID studentId) {
        StudentEnrollment student = studentEnrollmentRepository.getStudentById(studentId)
                .orElseThrow(()->new NotFoundException("Student not found!"));
        return student;
    }

    @Override
    public Long countOfStudents() {
        Long count = studentEnrollmentRepository.countOfStudents();
        return count;
    }

    @Override
    public List<StudentEnrollment> getStudentsByBranch(UUID branchId) {
        List<StudentEnrollment> students = studentEnrollmentRepository.getStudentsByBranch(branchId);
        return students;
    }

    @Override
    public List<StudentsGroupInfoDto> getStudentsInfoByGroup(UUID groupId) {
        List<StudentProjection> students = studentEnrollmentRepository.getStudentsProjection(groupId);
        List<StudentsGroupInfoDto> mappedStudents = students.stream().map(StudentMapper::projectionToDto).toList();
        return mappedStudents;
    }
}
