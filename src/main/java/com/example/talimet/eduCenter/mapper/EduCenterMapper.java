package com.example.talimet.eduCenter.mapper;

import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.dto.response.EduCenterCreateResponseDto;
import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.user.entity.User;

public class EduCenterMapper {
    public static EduCenter dtoToEntity(EduCenterRequestDto dto, User owner){
        EduCenter eduCenter = new EduCenter();
        eduCenter.setName(dto.name());
        eduCenter.setAddress(dto.address());
        eduCenter.setPhoneNumber(dto.phoneNumber());
        eduCenter.setOwner(owner);
        return eduCenter;
    }

    public static EduCenterCreateResponseDto entityToCreateDto(EduCenter entity, String message){
        return new EduCenterCreateResponseDto(
                entity.getId().toString(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhoneNumber(),
                message
        );
    }
    public static EduCenterResponseDto entityToDto(EduCenter entity){
        return new EduCenterResponseDto(
                entity.getId().toString(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhoneNumber(),
                entity.getOwner().getFirstName()+" "+entity.getOwner().getLastName(),
                entity.getCreatedAt()
        );
    }

}
