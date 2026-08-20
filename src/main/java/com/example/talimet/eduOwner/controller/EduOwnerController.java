package com.example.talimet.eduOwner.controller;


import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.service.EduService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/owner")
@RequiredArgsConstructor
@Tag(
        name = "Owner"
)
public class EduOwnerController {
     private final EduService service;

     @GetMapping("/{ownerId}/centers")
     public ResponseEntity<List<EduCenterResponseDto>> getEduCentersByOwner(@PathVariable UUID ownerId){
         List<EduCenter> centers = service.getEduCentersByOwnerId(ownerId);
         return ResponseEntity.ok(centers.stream().map(EduCenterMapper::entityToDto).collect(Collectors.toList()));
     }
}
