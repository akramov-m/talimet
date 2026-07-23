package com.example.talimet.eduCenter.service;

import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.entity.EduCenter;

import java.util.List;

public interface EduService {
    EduCenter create(EduCenterRequestDto dto);
    List<EduCenter> getAllEduCenters();
}
