package com.example.talimet.studentEnrollment.service.impl;

import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.studentEnrollment.dto.request.StudentEnrollmentRequestDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.mapper.StudentEnrollmentMapper;
import com.example.talimet.studentEnrollment.repository.StudentEnrollmentRepository;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
