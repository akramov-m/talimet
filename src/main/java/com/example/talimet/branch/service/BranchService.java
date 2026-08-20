package com.example.talimet.branch.service;

import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.entity.Branch;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    Branch create(BranchRequestDto dto);
    List<Branch> gettAllBranches();
    List<Branch> getBranchesByStudent(UUID studentId,UUID eduCenterId);
    List<Branch> getAllBranchesByCenterAndUser(UUID centerId,UUID userId);
    List<Branch> getAllBranchesByCenter(UUID centerId);
}
