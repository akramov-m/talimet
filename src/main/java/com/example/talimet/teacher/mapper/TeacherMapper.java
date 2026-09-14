package com.example.talimet.teacher.mapper;

import com.example.talimet.teacher.dto.response.TeacherDto;
import com.example.talimet.user.entity.User;

public class TeacherMapper {

    public static TeacherDto entityToDto(User entity){
        return new TeacherDto(
                entity.getId(),
                entity.getFirstName()+ " " + entity.getLastName(),
                entity.getUsername(),
                entity.getPhoneNumber()
        );
    }
}
