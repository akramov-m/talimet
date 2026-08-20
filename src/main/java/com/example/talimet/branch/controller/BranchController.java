package com.example.talimet.branch.controller;


import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.dto.response.BranchResponseCreateDto;
import com.example.talimet.branch.dto.response.BranchResponseDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.mapper.BranchMapper;
import com.example.talimet.branch.service.BranchService;
import com.example.talimet.group.dto.response.GroupsInfoDtoByBranch;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.student.dto.response.StudentResponseDto;
import com.example.talimet.student.dto.response.StudentsResponseDto;
import com.example.talimet.student.mapper.StudentMapper;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.subject.dto.response.SubjectInfoDto;
import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.mapper.SubjectMapper;
import com.example.talimet.subject.repository.interfaces.SubjectInfoProjection;
import com.example.talimet.subject.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/branch")
@RequiredArgsConstructor
@Tag(
        name = "Branch"
)
public class BranchController {

    private final BranchService branchService;
    private final SubjectService subjectService;
    private final StudentEnrollmentService studentService;
    private final GroupService groupService;
    @PostMapping("/create")
    public ResponseEntity<BranchResponseCreateDto> create(@RequestBody BranchRequestDto dto){
        Branch branch = branchService.create(dto);
        String message = "is added to" + branch.getEduCenter().getName();
        BranchResponseCreateDto response = BranchMapper.entityToCreateDto(branch,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BranchResponseDto>> getAllBranches(){
        List<Branch> branches = branchService.gettAllBranches();
        return ResponseEntity.ok(branches.stream().map(BranchMapper::entityToDto).collect(Collectors.toList()));
    }


    @GetMapping("/{branchId}/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjectsByBranch(@PathVariable UUID branchId){
        List<Subject> subjects = subjectService.getSubjectsByBranch(branchId);
        return ResponseEntity.ok(subjects.stream().map(SubjectMapper::entityToDto).collect(Collectors.toList()));
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByBranch(@RequestParam UUID branchId){
        List<StudentEnrollment> students = studentService.getStudentsByBranch(branchId);
        return ResponseEntity.ok(students.stream().map(StudentMapper::entityToStudentDto).collect(Collectors.toList()));
    }

    @GetMapping("/subjects/info")
    public ResponseEntity<List<SubjectInfoDto>> getSubjectsInfoByBranch(@RequestParam UUID branchId){
        List<SubjectInfoProjection> subjectInfoProjections = subjectService.getSubjectsInfoByBranch(branchId);
        return ResponseEntity.ok(subjectInfoProjections.stream().map(SubjectMapper::projectionToDto).collect(Collectors.toList()));
    }


    @GetMapping("/groups/info")
    public ResponseEntity<List<GroupsInfoDtoByBranch>> getGroupsInfoByBranch(@RequestParam UUID branchId){
        List<GroupsInfoProjectionByBranch> groups = groupService.getGroupsInfoByBranch(branchId);
        return ResponseEntity.ok(groups.stream().map(GroupMapper::projectionToDtoByBranch).toList());
    }
}
