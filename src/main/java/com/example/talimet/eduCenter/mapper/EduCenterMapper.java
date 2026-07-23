package com.example.talimet.eduCenter.mapper;

import com.example.talimet.eduCenter.dto.request.EduCenterRequestDto;
import com.example.talimet.eduCenter.dto.response.EduCenterCreateResponseDto;
import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;

public class EduCenterMapper {
    public static EduCenter dtoToEntity(EduCenterRequestDto dto){
        EduCenter eduCenter = new EduCenter();
        eduCenter.setName(dto.name());
        eduCenter.setAddress(dto.address());
        eduCenter.setPhoneNumber(dto.phoneNumber());
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
                entity.getPhoneNumber()
        );
    }

}
