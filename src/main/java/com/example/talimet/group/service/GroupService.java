package com.example.talimet.group.service;

import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.GroupCreateResponseDto;
import com.example.talimet.group.dto.response.GroupDetailsDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionBySubject;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    GroupCreateResponseDto create(GroupRequestDto dto);
    List<Group> getAllGroups();
    List<Group> getGroupsByStudent(UUID studentId, UUID subjectId);
    List<Group> getGroupsBySubject(UUID subjectId);
    List<GroupsInfoProjectionBySubject> getGroupsInfoProjection(UUID subjectId);
    GroupDetailsDto getGroupDetailsById(UUID groupId);
    List<GroupsInfoProjectionByBranch> getGroupsInfoByBranch(UUID branchId);
}
