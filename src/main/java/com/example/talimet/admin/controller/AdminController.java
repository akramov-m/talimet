package com.example.talimet.admin.controller;


import com.example.talimet.user.dto.request.UserChangeStatusRequest;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.dto.response.UserStatusChangedResponse;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import com.example.talimet.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/admin")
@RequiredArgsConstructor
@Tag(
        name = "Admin"
)
public class AdminController {
    private final UserService userService;

    @GetMapping("/users/inActive")
    public ResponseEntity<List<UserResponseDto>> getAllInActiveUsers(){
        List<User> users = userService.getAllInActiveUsers();
        return ResponseEntity.ok(users.stream().map(UserMapper::entityToDto).collect(Collectors.toList()));
    }


    @PostMapping("/user/{userId}/activate")
    public ResponseEntity<UserStatusChangedResponse> activeUser(@PathVariable UUID userId){
        User user=userService.activateUser(userId);
        String message = "Status changed successfully!";
        UserStatusChangedResponse changedUser = UserMapper.entityToStatusDto(user,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(changedUser);
    }

    @PostMapping("/user/changeStatus")
    public ResponseEntity<UserStatusChangedResponse> changeUserStatus(@RequestBody UserChangeStatusRequest dto){
        User user = userService.changeUserStatus(dto);
        String message = "Status changed successfully!";
        UserStatusChangedResponse changedUser = UserMapper.entityToStatusDto(user,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(changedUser);
    }
}
