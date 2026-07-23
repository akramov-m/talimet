package com.example.talimet.subject.service;

import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.entity.Subject;

import java.util.List;

public interface SubjectService {
    Subject create(SubjectRequestDto dto);
    List<Subject> getAllSubjects();
}
