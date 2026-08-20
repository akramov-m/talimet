package com.example.talimet.auth.service.impl;

import com.example.talimet.auth.dto.login.request.UserLoginRequest;
import com.example.talimet.auth.dto.register.request.UserRegisterRequest;
import com.example.talimet.auth.service.AuthService;
import com.example.talimet.common.enums.AccountStatus;
import com.example.talimet.common.enums.Role;
import com.example.talimet.common.exception.ForbiddenException;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import com.example.talimet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User login(UserLoginRequest dto) {
        User user = userRepository.findByPhoneNumber(dto.phoneNumber()).orElseThrow(()->new NotFoundException("Phone number is not found"));
        if (!user.getPassword().equals(dto.password())){
            throw new NotFoundException("Password is incorrect!");
        }
        if (user.getStatus()!=AccountStatus.ACTIVE){
            throw new ForbiddenException("You are not activated yet!");
        }
        return user;
    }

    @Override
    public User register(UserRegisterRequest dto) {
        AccountStatus status =
                Role.valueOf(dto.role().toUpperCase()) == Role.OWNER
                        ? AccountStatus.INACTIVE
                        : AccountStatus.ACTIVE;

        User user = UserMapper.dtoToEntity(dto,status);
        User savedUser = userRepository.save(user);
        return savedUser;
    }


}
