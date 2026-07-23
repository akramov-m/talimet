package com.example.talimet.user.controller;

import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import com.example.talimet.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = service.gettAllUsers();
        return ResponseEntity.ok(users.stream().map(UserMapper::entityToDto).collect(Collectors.toList()));
    }
}
