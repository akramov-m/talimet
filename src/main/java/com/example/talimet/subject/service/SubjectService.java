package com.example.talimet.subject.service;

import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.repository.interfaces.SubjectInfoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SubjectService {
    Subject create(SubjectRequestDto dto);
    List<Subject> getAllSubjects();
    List<Subject> getSubjectsByStudent(UUID studentId,UUID branchId);
    List<Subject> getSubjectsByBranch( UUID branchId);
    List<SubjectInfoProjection> getSubjectsInfoByBranch(UUID branchId);
    List<Subject> getSubjectsByTeacher(UUID teacherId,UUID branchId);
}
