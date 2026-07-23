package com.example.talimet.auth.service;

import com.example.talimet.auth.dto.login.request.UserLoginRequest;
import com.example.talimet.auth.dto.register.request.UserRegisterRequest;
import com.example.talimet.user.entity.User;

import java.util.Optional;

public interface AuthService {
    User login(UserLoginRequest dto);
    User register(UserRegisterRequest dto);
}
