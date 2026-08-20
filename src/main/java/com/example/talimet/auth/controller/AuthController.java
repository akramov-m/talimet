package com.example.talimet.auth.controller;


import com.example.talimet.auth.dto.login.request.UserLoginRequest;
import com.example.talimet.auth.dto.login.response.UserLoginResponse;
import com.example.talimet.auth.dto.register.request.UserRegisterRequest;
import com.example.talimet.auth.dto.register.response.UserRegisterResponse;
import com.example.talimet.auth.service.AuthService;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edu/auth")
@RequiredArgsConstructor
@Tag(
        name = "Auth"
)
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest dto){
        User user = authService.login(dto);
        String message = "Login successfully!";
        UserLoginResponse response = UserMapper.entityToLoginDto(user,message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest dto){
        User user = authService.register(dto);
        String message = "Register successfully!";
        UserRegisterResponse response = UserMapper.entityToRegisterDto(user,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
