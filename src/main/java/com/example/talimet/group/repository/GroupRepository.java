package com.example.talimet.group.repository;

import com.example.talimet.group.entity.Group;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionByBranch;
import com.example.talimet.group.repository.interfaces.GroupsInfoProjectionBySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    Optional<Group> findById(UUID uuid);


    @Query("""
    SELECT DISTINCT g
    FROM StudentEnrollment se
    JOIN se.group g
    LEFT JOIN FETCH g.lessonDays ld
    WHERE se.student.id =:studentId
    AND g.subject.id =:subjectId
    """)
    List<Group> getGroupsByStudent(@Param("studentId") UUID studentId, @Param("subjectId") UUID subjectId);


    @Query("""
    SELECT DISTINCT g
    FROM Group g
    LEFT JOIN g.lessonDays
    WHERE g.subject.id=:subjectId
    """)
    List<Group> getGroupsBySubject(@Param("subjectId") UUID subjectId);

    @Query("""
    SELECT 
        g.id AS groupId,
        g.name AS groupName,
        g.description AS description,
        g.lessonTime AS lessonTime,
        COUNT(DISTINCT se.student.id) AS studentCount,
        COUNT(DISTINCT te.teacher.id) AS teacherCount
    FROM Group g
    LEFT JOIN StudentEnrollment se ON se.group.id = g.id
    LEFT JOIN TeacherEnrollment te ON te.group.id = g.id
    WHERE g.subject.id = :subjectId
    GROUP BY g.id, g.name, g.description,g.lessonTime
    """)
    List<GroupsInfoProjectionBySubject> getGroupsInfoBySubject(@Param("subjectId") UUID subjectId);

    @Query("""
    SELECT
        g.id as groupId,
        g.name as groupName,
        g.description as description,
        g.lessonTime as lessonTime,
        g.subject.name as subjectName,
        COUNT(DISTINCT se.student.id) as studentCount
    FROM Group g
    JOIN Subject s ON g.subject.id = s.id
    LEFT JOIN StudentEnrollment se ON se.group.id = g.id
    WHERE s.branch.id = :branchId
    GROUP BY g.id, g.name, g.description, g.subject.name,g.lessonTime
    """)
    List<GroupsInfoProjectionByBranch> getGroupsInfoByBranch(@Param("branchId") UUID branchId);
}
