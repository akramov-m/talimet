package com.example.talimet.group.service.impl;

import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.GroupCreateResponseDto;
import com.example.talimet.group.dto.response.GroupDetailsDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionBySubject;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.lessonDays.dto.response.LessonDaysResponseDto;
import com.example.talimet.lessonDays.entity.LessonDays;
import com.example.talimet.lessonDays.mapper.LessonDaysMapper;
import com.example.talimet.lessonDays.service.LessonDaysService;
import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.repository.SubjectRepository;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;
import com.example.talimet.teacherEnrollment.service.TeacherEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final StudentEnrollmentService studentService;
    private final TeacherEnrollmentService teacherService;
    private final LessonDaysService lessonDaysService;


    @Override
    public GroupCreateResponseDto create(GroupRequestDto dto) {
        Subject subject = subjectRepository.findById(dto.subjectId())
                .orElseThrow(()->new NotFoundException("Subject not found"));
        Group group = GroupMapper.dtoToEntity(dto,subject);
        Group savedGroup = groupRepository.save(group);
        List<LessonDays> lessonDays=lessonDaysService.create(savedGroup,dto.lessonDays());
        List<LessonDaysResponseDto> mappedLessonDays = lessonDays.stream().map(LessonDaysMapper::entityToDto).toList();
        String message = "Group successfully created!";
        return GroupMapper.entityCreateToDto(savedGroup,mappedLessonDays,message);
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    @Override
    public List<Group> getGroupsByStudent(UUID studentId, UUID subjectId) {
        List<Group> groups = groupRepository.getGroupsByStudent(studentId,subjectId);
        return groups;
    }

    @Override
    public List<Group> getGroupsBySubject(UUID subjectId) {
        List<Group> groups = groupRepository.getGroupsBySubject(subjectId);
        return groups;
    }

    @Override
    public List<GroupsInfoProjectionBySubject> getGroupsInfoProjection(UUID subjectId) {
        List<GroupsInfoProjectionBySubject> groupsInfoProjections = groupRepository.getGroupsInfoBySubject(subjectId);
        return groupsInfoProjections;
    }

    @Override
    public GroupDetailsDto getGroupDetailsById(UUID groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(()->new NotFoundException("Group not found!"));
        List<StudentsGroupInfoDto> students = studentService.getStudentsInfoByGroup(groupId);
        List<TeachersInfo> teachers = teacherService.getTeachersInfoByGroup(groupId);
        GroupDetailsDto groupDetails = GroupMapper.detailsEntitiesToDto(group,students,teachers);
        return groupDetails;
    }

    @Override
    public List<GroupsInfoProjectionByBranch> getGroupsInfoByBranch(UUID branchId) {
        List<GroupsInfoProjectionByBranch> groups = groupRepository.getGroupsInfoByBranch(branchId);
        return groups;
    }
}
