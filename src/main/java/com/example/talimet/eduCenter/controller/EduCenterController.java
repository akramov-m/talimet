package com.example.talimet.eduCenter.controller;


import com.example.talimet.branch.dto.response.BranchResponseDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.mapper.BranchMapper;
import com.example.talimet.branch.service.BranchService;
import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.dto.response.EduCenterCreateResponseDto;
import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.service.EduService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu")
@RequiredArgsConstructor
@Tag(
        name = "Education center"
)
public class EduCenterController {
    private final EduService eduService;
    private final BranchService branchService;
    @PostMapping("/create")
    public ResponseEntity<EduCenterCreateResponseDto> create(@RequestBody  EduCenterRequestDto dto){
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


    @GetMapping("/{centerId}/{userId}/branches")
    public ResponseEntity<List<BranchResponseDto>> getAllBranchesByCenterAndUser(@PathVariable UUID centerId,@PathVariable UUID userId){
        List<Branch> branches = branchService.getAllBranchesByCenterAndUser(centerId,userId);
        return ResponseEntity.ok(branches.stream().map(BranchMapper::entityToDto).collect(Collectors.toList()));
    }


    @GetMapping("/{centerId}/branches")
    public ResponseEntity<List<BranchResponseDto>> getAllBranchesByCenter(@PathVariable UUID centerId){
        List<Branch> branches = branchService.getAllBranchesByCenter(centerId);
        return ResponseEntity.ok(branches.stream().map(BranchMapper::entityToDto).toList());
    }


}
