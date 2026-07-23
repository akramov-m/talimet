package com.example.talimet.group.controller;


import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.GroupCreateResponseDto;
import com.example.talimet.group.dto.response.GroupResponseDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/edu/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupCreateResponseDto> create(GroupRequestDto dto){
        Group group = groupService.create(dto);
        String message = "is added to" + " " + group.getSubject();
        GroupCreateResponseDto response = GroupMapper.entityCreateToDto(group,message);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> getAllGroups(){
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups.stream().map(GroupMapper::entityToDto).collect(Collectors.toList()));
    }
}

