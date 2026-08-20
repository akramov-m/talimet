package com.example.talimet.subject.mapper;

import com.example.talimet.branch.entity.Branch;
import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.dto.response.SubjectCreateResponseDto;
import com.example.talimet.subject.dto.response.SubjectInfoDto;
import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.repository.interfaces.SubjectInfoProjection;

public class SubjectMapper {
    public static Subject dtoToEntity(SubjectRequestDto dto, Branch branch){
        Subject subject = new Subject();
        subject.setName(dto.name());
        subject.setDescription(dto.description());
        subject.setBranch(branch);

        return subject;
    };

    public static SubjectCreateResponseDto entityCreateToDto(Subject entity, String message){
        return new SubjectCreateResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                message
        );
    }

    public static SubjectResponseDto entityToDto(Subject entity){
        return new SubjectResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getBranch().getBranchName(),
                entity.getBranch().getId()
        );
    }


    public static SubjectInfoDto projectionToDto(SubjectInfoProjection projection){
        return new SubjectInfoDto(
                projection.getSubjectId(),
                projection.getSubjectName(),
                projection.getDescription(),
                projection.getStudentCount(),
                projection.getGroupCount()
        );
    }

}
