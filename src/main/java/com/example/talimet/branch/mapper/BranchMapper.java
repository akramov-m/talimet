package com.example.talimet.branch.mapper;

import com.example.talimet.branch.dto.request.BranchRequestDto;
import com.example.talimet.branch.dto.response.BranchResponseCreateDto;
import com.example.talimet.branch.dto.response.BranchResponseDto;
import com.example.talimet.branch.entity.Branch;
import com.example.talimet.eduCenter.entity.EduCenter;

public class BranchMapper {
    public static Branch dtoToEntity (BranchRequestDto dto, EduCenter center){
        Branch branch = new Branch();
        branch.setBranchName(dto.name());
        branch.setBranchAddress(dto.address());
        branch.setEduCenter(center);
        return branch;
    }

    public static BranchResponseCreateDto entityToCreateDto(Branch entity, String message){
        return new BranchResponseCreateDto(
                entity.getId().toString(),
                entity.getBranchName(),
                message
        );
    }

    public static BranchResponseDto entityToDto(Branch entity){
        return new BranchResponseDto(
                entity.getId().toString(),
                entity.getBranchName()
        );
    }
}
