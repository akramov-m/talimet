package com.example.talimet.subject.controller;

import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.dto.response.SubjectCreateResponseDto;
import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.mapper.SubjectMapper;
import com.example.talimet.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService service;

    @PostMapping("/create")
    public ResponseEntity<SubjectCreateResponseDto> create(SubjectRequestDto dto){
        Subject subject = service.create(dto);
        String message = "is added to" + " " + subject.getBranch();
        SubjectCreateResponseDto response = SubjectMapper.entityCreateToDto(subject,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjects(){
        List<Subject> subjects = service.getAllSubjects();
        return ResponseEntity.ok(subjects.stream().map(SubjectMapper::entityToDto).collect(Collectors.toList()));
    }
}
