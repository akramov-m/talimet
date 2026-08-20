package com.example.talimet.subject.service.impl;

import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.repository.BranchRepository;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.mapper.SubjectMapper;
import com.example.talimet.subject.repository.SubjectRepository;
import com.example.talimet.subject.repository.interfaces.SubjectInfoProjection;
import com.example.talimet.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final BranchRepository branchRepository;

    @Override
    public Subject create(SubjectRequestDto dto) {
        Branch branch = branchRepository.findById(dto.branchId())
                .orElseThrow(()-> new NotFoundException("Branch not found!"));
        Subject subject = SubjectMapper.dtoToEntity(dto,branch);
        Subject savedSubject = subjectRepository.save(subject);
        return savedSubject;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects;
    }

    @Override
    public List<Subject> getSubjectsByStudent(UUID studentId, UUID branchId) {
        List<Subject> subjects = subjectRepository.getSubjectsByStudent(studentId,branchId);
        return subjects;
    }

    @Override
    public List<Subject> getSubjectsByBranch(UUID branchId) {
        List<Subject> subjects = subjectRepository.getSubjectsByBranch(branchId);
        return subjects;
    }

    @Override
    public List<SubjectInfoProjection> getSubjectsInfoByBranch(UUID branchId) {
        List<SubjectInfoProjection> subjectInfoProjections = subjectRepository.getSubjectsInfoByBranch(branchId);
        return subjectInfoProjections;
    }

    @Override
    public List<Subject> getSubjectsByTeacher(UUID teacherId, UUID branchId) {
        return subjectRepository.getSubjectsByTeacher(teacherId,branchId);
    }
}
