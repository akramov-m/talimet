package com.example.talimet.group.service.impl;

import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    @Override
    public Group create(GroupRequestDto dto) {
        Subject subject = subjectRepository.findById(dto.subjectId())
                .orElseThrow(()->new NotFoundException("Subject not found"));
        Group group = GroupMapper.dtoToEntity(dto,subject);
        Group savedGroup = groupRepository.save(group);
        return savedGroup;
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }
}
