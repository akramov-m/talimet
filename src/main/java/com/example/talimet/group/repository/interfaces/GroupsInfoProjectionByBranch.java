package com.example.talimet.group.repository.interfaces;

import java.util.UUID;

public interface GroupsInfoProjectionByBranch {
    UUID getGroupId();
    String getGroupName();
    String getDescription();
    String getLessonTime();
    String getSubjectName();
    Long getStudentCount();

}
