package com.example.talimet.branch.service;

import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.entity.Branch;

import java.util.List;

public interface BranchService {
    Branch create(BranchRequestDto dto);
    List<Branch> gettAllBranches();
}
