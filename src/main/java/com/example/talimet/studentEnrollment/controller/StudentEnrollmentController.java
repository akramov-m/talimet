package com.example.talimet.studentEnrollment.controller;


import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.studentEnrollment.dto.request.StudentEnrollmentRequestDto;
import com.example.talimet.studentEnrollment.dto.response.StudentEnrollmentResponseDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.mapper.StudentEnrollmentMapper;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edu/studentEnrollment")
@RequiredArgsConstructor
public class StudentEnrollmentController {

    private final StudentEnrollmentService service;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @PostMapping("/create")
    public ResponseEntity<StudentEnrollmentResponseDto> create(StudentEnrollmentRequestDto dto){
        StudentEnrollment student = service.create(dto);
        User user = userRepository.findById(student.getStudent().getId())
                .orElseThrow(()->new NotFoundException("User not found in Controller"));

        Group group = groupRepository.findById(student.getGroup().getId())
                .orElseThrow(()->new NotFoundException("Group not found in Controller"));

        String message = user.getFirstName()+" "+user.getLastName()+" "+"added successfully! to" + " " + group.getName();
        StudentEnrollmentResponseDto response = StudentEnrollmentMapper.entityToDto(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
