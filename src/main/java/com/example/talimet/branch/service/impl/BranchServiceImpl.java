package com.example.talimet.branch.service.impl;

import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.mapper.BranchMapper;
import com.example.talimet.branch.repository.BranchRepository;
import com.example.talimet.branch.service.BranchService;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.repository.EduCenterRepository;
import com.example.talimet.eduCenter.service.EduService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final EduCenterRepository eduCenterRepository;
    @Override
    public Branch create(BranchRequestDto dto) {
        EduCenter center = eduCenterRepository.findById(dto.eduCenterId()).orElseThrow(()->new NotFoundException("Education Center not found!"));
        Branch branch = BranchMapper.dtoToEntity(dto,center);
        Branch savedBranch = branchRepository.save(branch);
        return savedBranch;
    }

    @Override
    public List<Branch> gettAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches;
    }
}
