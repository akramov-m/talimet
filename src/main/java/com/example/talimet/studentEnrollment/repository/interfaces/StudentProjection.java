package com.example.talimet.studentEnrollment.repository.interfaces;

import java.util.UUID;

public interface StudentProjection {
    UUID getStudentId();
    String getStudentFirstName();
    String getStudentLastName();
    String getStudentUsername();
}
