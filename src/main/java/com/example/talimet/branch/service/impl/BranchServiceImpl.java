package com.example.talimet.branch.service.impl;

import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.branch.mapper.BranchMapper;
import com.example.talimet.branch.repository.BranchRepository;
import com.example.talimet.branch.service.BranchService;
import com.example.talimet.common.enums.Role;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.repository.EduCenterRepository;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final EduCenterRepository eduCenterRepository;
    private final UserRepository userRepository;
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

    @Override
    public List<Branch> getBranchesByStudent(UUID studentId, UUID eduCenterId) {
        List<Branch> branches = branchRepository.getBranchesByStudent(studentId,eduCenterId);
        return branches;
    }

    @Override
    public List<Branch> getAllBranchesByCenterAndUser(UUID centerId,UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found!"));
        if(user.getRole()== Role.OWNER) {
            return branchRepository.getAllBranchesByCenterId(centerId);
        }
        if (user.getRole()==Role.STUDENT){
            return  branchRepository.getBranchesByStudent(userId,centerId);
        }

        if (user.getRole()==Role.TEACHER){
            return  branchRepository.getAllBranchesByTeacher(userId,centerId);
        }

        return Collections.emptyList();
    }

    @Override
    public List<Branch> getAllBranchesByCenter(UUID centerId) {
        List<Branch> branches = branchRepository.getAllBranchesByCenterId(centerId);
        return branches;
    }
}
