package com.example.talimet.eduCenter.service.impl;


import com.example.talimet.common.enums.Role;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.repository.EduCenterRepository;
import com.example.talimet.eduCenter.service.EduService;
import com.example.talimet.studentEnrollment.repository.StudentEnrollmentRepository;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EduServiceImpl implements EduService {

    private final EduCenterRepository eduCenterRepository;
    private final UserRepository userRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    @Override
    public EduCenter create(EduCenterRequestDto dto) {
        User user =userRepository.findById(dto.ownerId())
                .orElseThrow(()->new NotFoundException("Owner not found"));
        if (user.getRole()!= Role.OWNER){
            throw new RuntimeException("You can not create Education center!");
        }
        EduCenter eduCenter = EduCenterMapper.dtoToEntity(dto,user);
        EduCenter savedEduCenter = eduCenterRepository.save(eduCenter);
        return savedEduCenter;
    }

    @Override
    public List<EduCenter> getAllEduCenters() {
        List<EduCenter> centers = eduCenterRepository.findAll();
        return centers;
    }

    @Override
    public List<EduCenter> getEduCentersByStudentId(UUID studentUUID) {
        List<EduCenter> centers = eduCenterRepository.findByStudentId(studentUUID);
        return centers;
    }

    @Override
    public List<EduCenter> getEduCentersByOwnerId(UUID ownerId) {
        List<EduCenter> centers = eduCenterRepository.findAllByOwnerId(ownerId);
        return centers;
    }

    @Override
    public List<EduCenter> getEduCentersByUser(UUID userId, Role role) {

        if (role == Role.STUDENT) {
            return eduCenterRepository.findByStudentId(userId);
        }

        if (role == Role.OWNER) {
            return eduCenterRepository.findAllByOwnerId(userId);
        }
        if (role == Role.TEACHER){
            return eduCenterRepository.getEduCentersByTeacher(userId);
        }

        return Collections.emptyList();
    }

    @Override
    public Long countOfEduCenter() {
        Long counts = eduCenterRepository.countOfEduCenters();
        return counts;
    }


}
