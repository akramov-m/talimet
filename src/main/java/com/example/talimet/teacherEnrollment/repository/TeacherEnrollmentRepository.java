package com.example.talimet.teacherEnrollment.repository;

import com.example.talimet.teacherEnrollment.entity.TeacherEnrollment;
import com.example.talimet.teacherEnrollment.repository.interfaces.TeacherInfoProjectionByGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TeacherEnrollmentRepository extends JpaRepository<TeacherEnrollment, UUID> {

    @Query("""
    SELECT
         te.teacher.id AS teacherId,
         te.teacher.firstName AS teacherFirstName,
         te.teacher.lastName AS teacherLastName
   FROM TeacherEnrollment te
   WHERE te.group.id =:groupId
    """)
    List<TeacherInfoProjectionByGroup> getTeachersInfoByGroup(@Param("groupId") UUID groupId);
}
