package com.example.talimet.group.mapper;

import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.GroupCreateResponseDto;
import com.example.talimet.group.dto.response.GroupResponseDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.subject.entity.Subject;

public class GroupMapper {
    public static Group dtoToEntity (GroupRequestDto dto, Subject subject){
        Group group = new Group();
        group.setName(dto.name());
        group.setSubject(subject);

        return group;
    }

    public static GroupCreateResponseDto entityCreateToDto(Group group, String message){
        return new GroupCreateResponseDto(
                group.getId().toString(),
                group.getName(),
                message
        );
    }

    public static GroupResponseDto entityToDto(Group group){
        return new GroupResponseDto(
                group.getId().toString(),
                group.getName()
        );
    }
}
