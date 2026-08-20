package com.example.talimet.subject.repository.interfaces;

import java.util.UUID;

public interface SubjectInfoProjection {
    UUID getSubjectId();
    String getSubjectName();
    String getDescription();
    Long getStudentCount();
    Long getGroupCount();
}
