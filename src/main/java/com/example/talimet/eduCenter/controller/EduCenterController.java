package com.example.talimet.eduCenter.controller;


import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.dto.response.EduCenterCreateResponseDto;
import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.service.EduService;
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
@RequestMapping("/edu")
@RequiredArgsConstructor
public class EduCenterController {
    private final EduService eduService;

    @PostMapping("/create")
    public ResponseEntity<EduCenterCreateResponseDto> create(EduCenterRequestDto dto){
        EduCenter eduCenter = eduService.create(dto);
        String message = "Education center created successfully!";
        EduCenterCreateResponseDto response = EduCenterMapper.entityToCreateDto(eduCenter,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EduCenterResponseDto>> getAllEduCenters(){
        List<EduCenter> centers = eduService.getAllEduCenters();
        return ResponseEntity.ok(centers.stream().map(EduCenterMapper::entityToDto).collect(Collectors.toList()));
    }
}
