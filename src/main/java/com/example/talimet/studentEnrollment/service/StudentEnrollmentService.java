package com.example.talimet.studentEnrollment.service;

import com.example.talimet.studentEnrollment.dto.request.StudentEnrollmentRequestDto;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;

public interface StudentEnrollmentService {
    StudentEnrollment create(StudentEnrollmentRequestDto dto);
}
