package com.example.talimet.subject.controller;

import com.example.talimet.group.dto.response.GroupResponseDto;
import com.example.talimet.group.dto.response.GroupsInfoDtoBySubject;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionBySubject;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.dto.response.SubjectCreateResponseDto;
import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.mapper.SubjectMapper;
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
@RequestMapping("/edu/subject")
@RequiredArgsConstructor
@Tag(
        name = "Subject"
)
public class SubjectController {
    private final SubjectService service;
    private final GroupService groupService;
    @PostMapping("/create")
    public ResponseEntity<SubjectCreateResponseDto> create(@RequestBody SubjectRequestDto dto){
        Subject subject = service.create(dto);
        String message = "is added to" + " " + subject.getBranch().getBranchName();
        SubjectCreateResponseDto response = SubjectMapper.entityCreateToDto(subject,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjects(){
        List<Subject> subjects = service.getAllSubjects();
        return ResponseEntity.ok(subjects.stream().map(SubjectMapper::entityToDto).collect(Collectors.toList()));
    }



    @GetMapping("/groups/info")
    public ResponseEntity<List<GroupsInfoDtoBySubject>> getGroupsInfoBySubject(@RequestParam UUID subjectId){
        List<GroupsInfoProjectionBySubject> groupsInfoProjection= groupService.getGroupsInfoProjection(subjectId);
        return ResponseEntity.ok(groupsInfoProjection.stream().map(GroupMapper::projectionToDto).collect(Collectors.toList()));
    }
}
