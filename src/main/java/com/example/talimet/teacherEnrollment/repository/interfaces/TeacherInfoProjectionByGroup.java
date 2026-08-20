package com.example.talimet.teacherEnrollment.repository.interfaces;

import java.util.UUID;

public interface TeacherInfoProjectionByGroup {
    UUID getTeacherId();
    String getTeacherFirstName();
    String getTeacherLastName();
}
