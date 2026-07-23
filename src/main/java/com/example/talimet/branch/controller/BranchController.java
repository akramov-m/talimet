package com.example.talimet.branch.controller;


import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.dto.response.BranchResponseCreateDto;
import com.example.talimet.branch.dto.response.BranchResponseDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.mapper.BranchMapper;
import com.example.talimet.branch.service.BranchService;
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
@RequestMapping("/edu/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/create")
    public ResponseEntity<BranchResponseCreateDto> create(BranchRequestDto dto){
        Branch branch = branchService.create(dto);
        String message = "is added to" + branch.getEduCenter();
        BranchResponseCreateDto response = BranchMapper.entityToCreateDto(branch,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BranchResponseDto>> getAllBranches(){
        List<Branch> branches = branchService.gettAllBranches();
        return ResponseEntity.ok(branches.stream().map(BranchMapper::entityToDto).collect(Collectors.toList()));
    }
}
