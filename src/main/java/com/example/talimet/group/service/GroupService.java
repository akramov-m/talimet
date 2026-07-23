package com.example.talimet.group.service;

import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.entity.Group;

import java.util.List;

public interface GroupService {
    Group create(GroupRequestDto dto);
    List<Group> getAllGroups();
}
