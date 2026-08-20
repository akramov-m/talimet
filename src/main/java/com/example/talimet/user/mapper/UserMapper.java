package com.example.talimet.user.mapper;

import com.example.talimet.auth.dto.login.response.UserLoginResponse;
import com.example.talimet.auth.dto.register.request.UserRegisterRequest;
import com.example.talimet.auth.dto.register.response.UserRegisterResponse;
import com.example.talimet.common.enums.AccountStatus;
import com.example.talimet.common.enums.Role;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.dto.response.UserStatusChangedResponse;
import com.example.talimet.user.entity.User;

public class UserMapper {
    public static User dtoToEntity (UserRegisterRequest dto, AccountStatus status){
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setPassword(dto.password());
        user.setUsername(dto.userName());
        user.setPhoneNumber(dto.phoneNumber());
        user.setRole(Role.valueOf(dto.role().toUpperCase()));
        user.setStatus(status);
        return user;
    }

    public static UserLoginResponse entityToLoginDto(User entity,String message){
        return new UserLoginResponse(
                entity.getId(), entity.getFirstName(),entity.getRole(), message
        );
    }

    public static UserRegisterResponse entityToRegisterDto(User entity,String message){
        return new UserRegisterResponse(
                entity.getId(),
                message
        );
    }

    public static UserResponseDto entityToDto(User entity){
        return new UserResponseDto(
                entity.getId().toString(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getPhoneNumber(),
                entity.getPassword(),
                entity.getRole().toString()
        );
    }

    public static UserStatusChangedResponse entityToStatusDto(User entity,String message){
        return new UserStatusChangedResponse(
                entity.getFirstName()+ " " +entity.getLastName(),
                message
        );
    }
}
