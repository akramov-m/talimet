package com.example.talimet.user.controller;

import com.example.talimet.common.enums.Role;
import com.example.talimet.eduCenter.dto.request.EduCentersGetByUserDto;
import com.example.talimet.eduCenter.dto.response.EduCenterResponseDto;
import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.eduCenter.mapper.EduCenterMapper;
import com.example.talimet.eduCenter.service.EduService;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.mapper.UserMapper;
import com.example.talimet.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/users")
@RequiredArgsConstructor
@Tag(
        name = "User"
)
public class UserController {
    private final UserService service;
    private final EduService eduService;
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = service.gettAllUsers();
        return ResponseEntity.ok(users.stream().map(UserMapper::entityToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{userId}/mine")
    public ResponseEntity<UserResponseDto> getMyInformation(@PathVariable UUID userId){
        User user = service.getMyInformation(userId);
        UserResponseDto response = UserMapper.entityToDto(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/centers")
    public ResponseEntity<List<EduCenterResponseDto>> getEduCentersByUser(@RequestParam UUID userId,@RequestParam Role role ){
        List<EduCenter> eduCenters = eduService.getEduCentersByUser(userId,role);
        return ResponseEntity.ok(eduCenters.stream().map(EduCenterMapper::entityToDto).collect(Collectors.toList()));
    }
}
