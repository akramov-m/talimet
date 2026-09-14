package com.example.talimet.attendance.service.impl;

import com.example.talimet.attendance.dto.request.StudentAttendanceCreate;
import com.example.talimet.attendance.dto.response.StudentAttendanceCreateResponse;
import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;
import com.example.talimet.attendance.entity.Attendance;
import com.example.talimet.attendance.mapper.StudentAttendanceMapper;
import com.example.talimet.attendance.repository.StudentAttendanceRepository;
import com.example.talimet.attendance.service.StudentAttendanceService;
import com.example.talimet.common.enums.AttendanceEnum;
import com.example.talimet.common.exception.NotFoundException;
import com.example.talimet.lesson.entity.Lesson;
import com.example.talimet.lesson.repository.GroupLessonRepository;
import com.example.talimet.lesson.service.GroupLessonService;
import com.example.talimet.studentEnrollment.service.StudentEnrollmentService;
import com.example.talimet.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    private final StudentEnrollmentService studentEnrollmentService;
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final GroupLessonRepository groupLessonRepository;

    @Override
    public List<StudentAttendanceResponse> getAttendanceByLessonId(UUID lessonId) {

        Lesson lesson = groupLessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson not found!"));

        List<User> students =
                studentEnrollmentService.getStudentsByGroup(
                        lesson.getGroup().getId()
                );

        Map<UUID, AttendanceEnum> statusMap =
                studentAttendanceRepository.findByLessonId(lessonId)
                        .stream()
                        .collect(Collectors.toMap(
                                a -> a.getStudent().getStudent().getId(),
                                Attendance::getStatus
                        ));

        return StudentAttendanceMapper.entitiesToDto(
                students,
                statusMap
        );
    }



    @Override
    public StudentAttendanceCreateResponse saveAttendance(List<StudentAttendanceCreate> students, UUID lessonId) {
        Lesson lesson = groupLessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson not found!"));

        Set<UUID> validStudentsIds = studentEnrollmentService.getStudentsByGroup(lesson.getGroup().getId())
                .stream().map(User::getId).collect(Collectors.toSet());

        Map<UUID, Attendance> existing = studentAttendanceRepository.findByLessonId(lessonId)
                .stream()
                .collect(Collectors.toMap(
                        a -> a.getStudent().getStudent().getId(),
                        a -> a
                ));

        List<Attendance> toSave = new ArrayList<>();

        for (StudentAttendanceCreate student:students){
            if (!validStudentsIds.contains(student.studentId())){
                throw new IllegalArgumentException("Student isn't related to Group!");
            }

            Attendance attendance = existing.get(student.studentId());
            if (attendance==null){
                attendance = new Attendance();
                attendance.setLesson(lesson);
                attendance.setStudent(studentEnrollmentService.getStudentByIdAndGroup(student.studentId(),lesson.getGroup().getId()));
            }

            attendance.setStatus(student.status());
            toSave.add(attendance);
        }

        studentAttendanceRepository.saveAll(toSave);
        String message = "Davomat saqlandi";
        return new StudentAttendanceCreateResponse(lesson.getTitle(),message);
    }


}
