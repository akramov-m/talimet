package com.example.talimet.eduCenter.service.impl;


import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.repository.EduCenterRepository;
import com.example.talimet.eduCenter.service.EduService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduServiceImpl implements EduService {

    private final EduCenterRepository eduCenterRepository;

    @Override
    public EduCenter create(EduCenterRequestDto dto) {
        EduCenter eduCenter = EduCenterMapper.dtoToEntity(dto);
        EduCenter savedEduCenter = eduCenterRepository.save(eduCenter);
        return savedEduCenter;
    }

    @Override
    public List<EduCenter> getAllEduCenters() {
        List<EduCenter> centers = eduCenterRepository.findAll();
        return centers;
    }


}
