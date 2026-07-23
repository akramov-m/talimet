package com.example.talimet.subject.mapper;

import com.example.talimet.branch.entity.Branch;
import com.example.talimet.subject.dto.request.SubjectRequestDto;
import com.example.talimet.subject.dto.response.SubjectCreateResponseDto;
import com.example.talimet.subject.dto.response.SubjectResponseDto;
import com.example.talimet.subject.entity.Subject;

public class SubjectMapper {
    public static Subject dtoToEntity(SubjectRequestDto dto, Branch branch){
        Subject subject = new Subject();
        subject.setName(dto.name());
        subject.setBranch(branch);

        return subject;
    };

    public static SubjectCreateResponseDto entityCreateToDto(Subject entity, String message){
        return new SubjectCreateResponseDto(
                entity.getId().toString(),
                entity.getName(),
                message
        );
    }

    public static SubjectResponseDto entityToDto(Subject entity){
        return new SubjectResponseDto(
                entity.getId().toString(),
                entity.getName()
        );
    }

}
