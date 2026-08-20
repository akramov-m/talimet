package com.example.talimet.group.repository.interfaces;

import java.util.UUID;

public interface GroupsInfoProjectionBySubject {
    UUID getGroupId();
    String getGroupName();
    String getDescription();
    String getLessonTime();
    Long getStudentCount();
    Long getTeacherCount();
}
