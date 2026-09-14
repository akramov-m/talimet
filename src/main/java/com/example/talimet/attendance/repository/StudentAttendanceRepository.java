package com.example.talimet.attendance.repository;

import com.example.talimet.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentAttendanceRepository extends JpaRepository<Attendance, UUID> {

    List<Attendance> findByLessonId(UUID lessonId);

    Optional<Attendance> findByLessonIdAndStudentId(UUID lessonId,UUID studentId);
}
