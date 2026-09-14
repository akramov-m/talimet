package com.example.talimet.student.controller;


import com.example.talimet.branch.dto.response.BranchResponseDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.mapper.BranchMapper;
import com.example.talimet.branch.service.BranchService;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.service.EduService;
import com.example.talimet.group.dto.response.GroupResponseDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.student.dto.response.StudentResponseDto;
import com.example.talimet.student.mapper.StudentMapper;
import com.example.talimet.studentEnrollment.dto.request.StudentEnrollmentRequestDto;
import com.example.talimet.studentEnrollment.dto.response.StudentEnrollmentCreateResponseDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.mapper.StudentEnrollmentMapper;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.mapper.SubjectMapper;
import com.example.talimet.subject.service.SubjectService;
import com.example.talimet.user.dto.response.UserDto;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import com.example.talimet.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/student")
@RequiredArgsConstructor
@Tag(
        name = "Student"
)
public class StudentController {
    private final EduService eduService;
    private final BranchService branchService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final StudentEnrollmentService studentEnrollmentService;
    private final UserService userService;


    @GetMapping("/{studentId}/centers")
    public ResponseEntity<List<EduCenterResponseDto>> getEduCentersByStudent(@PathVariable UUID studentId){
        List<EduCenter> centers = eduService.getEduCentersByStudentId(studentId);
        return ResponseEntity.ok(centers.stream().map(EduCenterMapper::entityToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{eduCenterId}/{studentId}/branches")
    public ResponseEntity<List<BranchResponseDto>> getBranchesByStudent(@PathVariable UUID studentId,
                                                                        @PathVariable UUID eduCenterId){
        List<Branch> branches = branchService.getBranchesByStudent(studentId,eduCenterId);
        return ResponseEntity.ok(branches.stream().map(BranchMapper::entityToDto).collect(Collectors.toList()));
    }


    @GetMapping("/{branchId}/{studentId}/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getSubjectsByStudent(@PathVariable UUID studentId,
                                                                         @PathVariable UUID branchId){
        List<Subject> subjects = subjectService.getSubjectsByStudent(studentId,branchId);
        return ResponseEntity.ok(subjects.stream().map(SubjectMapper::entityToDto).collect(Collectors.toList()));
    }


    @GetMapping("/{subjectId}/{studentId}/groups")
    public ResponseEntity<List<GroupResponseDto>> getGroupsByStudent(@PathVariable UUID studentId,
                                                                     @PathVariable UUID subjectId){
        List<Group> groups = groupService.getGroupsByStudent(studentId,subjectId);
        return ResponseEntity.ok(groups.stream().map(GroupMapper::entityToDto).toList());
    }



    @GetMapping
    public ResponseEntity<UserDto> getStudentByUsername(@RequestParam String username){
        User user  = userService.getStudent(username);
        UserDto student = UserMapper.entityToUserDto(user);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/join-group")
    public ResponseEntity<StudentEnrollmentCreateResponseDto> joinGroup(@RequestBody StudentEnrollmentRequestDto dto){
        StudentEnrollment student = studentEnrollmentService.create(dto);
        String message = student.getStudent().getFirstName()+" "+student.getStudent().getLastName()+" "+"added successfully! to" + " " + student.getGroup().getName();
        StudentEnrollmentCreateResponseDto response = StudentEnrollmentMapper.entityToCreateDto(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
