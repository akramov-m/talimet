package com.example.talimet.subject.repository;

import com.example.talimet.subject.entity.Subject;
import com.example.talimet.subject.repository.interfaces.SubjectInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findById(UUID id);

    @Query("""
    SELECT DISTINCT s
    FROM StudentEnrollment se
    JOIN se.group g
    JOIN g.subject s
    WHERE se.student.id =:studentId
    AND s.branch.id =:branchId
""")
    List<Subject> getSubjectsByStudent(@Param("studentId") UUID studentId,@Param("branchId") UUID branchId);


    @Query("""
    SELECT DISTINCT s
    FROM TeacherEnrollment te
    JOIN te.group g
    JOIN g.subject s
    WHERE te.teacher.id =:teacherId AND s.branch.id=:branchId
    """)
    List<Subject> getSubjectsByTeacher(@Param("teacherId") UUID teacherId,@Param("branchId") UUID branchId);

    @Query("""
    SELECT DISTINCT s
    FROM Subject s
    WHERE s.branch.id =:branchId
    """)
    List<Subject> getSubjectsByBranch(@Param("branchId") UUID branchId);



    @Query("""
    SELECT 
        s.id AS subjectId,
        s.name AS subjectName,
        s.description AS description,
        COUNT (DISTINCT se.student.id) AS studentCount,
        COUNT (DISTINCT g.id) AS groupCount
    FROM Subject s
    LEFT JOIN Group g ON g.subject.id = s.id
    LEFT JOIN StudentEnrollment se ON se.group.id = g.id
    WHERE s.branch.id = :branchId
    GROUP BY s.id, s.name, s.description
    """)
    List<SubjectInfoProjection> getSubjectsInfoByBranch(@Param("branchId") UUID branchId);

}
