package com.example.talimet.studentEnrollment.repository;

import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, UUID> {
}
