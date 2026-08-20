package com.example.talimet.group.controller;


import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.*;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.lessonDays.entity.LessonDays;
import com.example.talimet.lessonDays.service.LessonDaysService;
import com.example.talimet.student.dto.response.StudentsResponseDto;
import com.example.talimet.student.mapper.StudentMapper;
import com.example.talimet.studentEnrollment.dto.response.StudentEnrollmentCreateResponseDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.mapper.StudentEnrollmentMapper;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/group")
@RequiredArgsConstructor
@Tag(
        name = "Group"
)
public class GroupController {
    private final GroupService groupService;
    private final StudentEnrollmentService studentEnrollmentService;
    private final LessonDaysService lessonDaysService;
    @PostMapping("/create")
    public ResponseEntity<GroupCreateResponseDto> create(@RequestBody GroupRequestDto dto){
        GroupCreateResponseDto group = groupService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupResponseNoDaysDto>> getAllGroups(){
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups.stream().map(GroupMapper::entityToDtoNoDays).toList());
    }


    @GetMapping("/{groupId}/students")
    public ResponseEntity<List<StudentsResponseDto>> getAllStudentEnrollmentsByGroup(@PathVariable UUID groupId){
        List<User> students= studentEnrollmentService.studentEnrollmentsByGroup(groupId);
        return ResponseEntity.ok(students.stream().map(StudentMapper::entityToGroupStudentDto).toList());
    }

    @GetMapping("/details")
    public ResponseEntity<GroupDetailsDto> getGroupDetails(@RequestParam UUID groupId){
        GroupDetailsDto response = groupService.getGroupDetailsById(groupId);
        return ResponseEntity.ok(response);
    }

}

