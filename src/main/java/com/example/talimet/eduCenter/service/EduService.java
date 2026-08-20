package com.example.talimet.eduCenter.service;

import com.example.talimet.common.enums.Role;
import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.entity.EduCenter;

import java.util.List;
import java.util.UUID;

public interface EduService {
    EduCenter create(EduCenterRequestDto dto);
    List<EduCenter> getAllEduCenters();
    List<EduCenter> getEduCentersByStudentId(UUID studentUUID);
    List<EduCenter> getEduCentersByOwnerId(UUID ownerId);
    List<EduCenter> getEduCentersByUser(UUID userId, Role role);

    Long countOfEduCenter();
}
