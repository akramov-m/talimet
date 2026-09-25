package com.example.talimet.group.service.impl;

import com.example.talimet.attendance.repository.StudentAttendanceRepository;
import com.example.talimet.common.enums.Role;
import com.example.talimet.common.exception.BadRequestException;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.group.dto.request.GroupJoinRequestDto;
import com.example.talimet.group.dto.request.GroupRequestDto;
import com.example.talimet.group.dto.response.GroupCreateResponseDto;
import com.example.talimet.group.dto.response.GroupDetailsDto;
import com.example.talimet.group.entity.Group;
import com.example.talimet.group.mapper.GroupMapper;
import com.example.talimet.group.repository.GroupRepository;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionBySubject;
import com.example.talimet.group.service.GroupService;
import com.example.talimet.lesson.dto.response.GroupLessonBodyResponse;
import com.example.talimet.lesson.entity.Lesson;
import com.example.talimet.lesson.mapper.GroupLessonMapper;
import com.example.talimet.lesson.service.GroupLessonService;
import com.example.talimet.lessonDays.dto.response.LessonDaysResponseDto;
import com.example.talimet.lessonDays.entity.LessonDays;
import com.example.talimet.lessonDays.mapper.LessonDaysMapper;
import com.example.talimet.lessonDays.service.LessonDaysService;
import com.example.talimet.student.dto.response.StudentsGroupInfoDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.mapper.StudentEnrollmentMapper;
import com.example.talimet.studentEnrollment.repository.StudentEnrollmentRepository;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.repository.SubjectRepository;
import com.example.talimet.teacherEnrollment.dto.response.TeachersInfo;
import com.example.talimet.teacherEnrollment.entity.TeacherEnrollment;
import com.example.talimet.teacherEnrollment.mapper.TeacherEnrollmentMapper;
import com.example.talimet.teacherEnrollment.repository.TeacherEnrollmentRepository;
import com.example.talimet.teacherEnrollment.service.TeacherEnrollmentService;
import com.example.talimet.user.entity.User;
import com.example.talimet.user.repository.UserRepository;
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
    private final GroupLessonService groupLessonService;
    private final UserRepository userRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final TeacherEnrollmentRepository teacherEnrollmentRepository;

    @Override
    public GroupCreateResponseDto create(GroupRequestDto dto,UUID subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
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
        List<Lesson> lessons = groupLessonService.getLessonsByGroup(groupId);
        List<GroupLessonBodyResponse> mappedLessons = lessons.stream().map(GroupLessonMapper::entityToDto).toList();
        GroupDetailsDto groupDetails = GroupMapper.detailsEntitiesToDto(group,students,teachers,mappedLessons);
        return groupDetails;
    }

    @Override
    public List<GroupsInfoProjectionByBranch> getGroupsInfoByBranch(UUID branchId) {
        List<GroupsInfoProjectionByBranch> groups = groupRepository.getGroupsInfoByBranch(branchId);
        return groups;
    }

    @Override
    public User joinGroup(GroupJoinRequestDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new NotFoundException("User not found!"));

        Group group = groupRepository.findById(dto.groupId())
                .orElseThrow(() -> new NotFoundException("Group not found!"));

        if (user.getRole() == Role.STUDENT) {
            StudentEnrollment student =
                    StudentEnrollmentMapper.dtoToEntity(user, group);

            StudentEnrollment savedStudent =
                    studentEnrollmentRepository.save(student);

            return savedStudent.getStudent();

        } else if (user.getRole() == Role.TEACHER) {
            TeacherEnrollment teacher =
                    TeacherEnrollmentMapper.createDtoToEntity(user, group);

            TeacherEnrollment savedTeacher =
                    teacherEnrollmentRepository.save(teacher);

            return savedTeacher.getTeacher();
        }

        throw new BadRequestException("User role cannot join a group!");
    }
}
