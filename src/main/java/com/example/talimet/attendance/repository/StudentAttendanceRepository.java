package com.example.talimet.attendance.repository;

import com.example.talimet.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentAttendanceRepository extends JpaRepository<Attendance, UUID> {
}
