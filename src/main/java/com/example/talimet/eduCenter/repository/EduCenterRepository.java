package com.example.talimet.eduCenter.repository;

import com.example.talimet.eduCenter.entity.EduCenter;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EduCenterRepository extends JpaRepository<EduCenter, UUID> {
    Optional<EduCenter> findById(UUID uuid);

    @Query("""
      SELECT DISTINCT b.eduCenter
      FROM StudentEnrollment se
      JOIN se.group g
      JOIN g.subject s
      JOIN s.branch b
      WHERE se.student.id = :studentId
      """)
    List<EduCenter> findByStudentId(@Param("studentId") UUID studentId);

    List<EduCenter> findAllByOwnerId(UUID ownerId);

    @Query("""
    SELECT COUNT(DISTINCT e.id)
    FROM EduCenter e
    """)
    Long countOfEduCenters();


    @Query("""
    SELECT DISTINCT b.eduCenter FROM TeacherEnrollment te
    JOIN te.group g
    JOIN g.subject s
    JOIN s.branch b
    WHERE te.teacher.id =:teacherId
    """)
    List<EduCenter> getEduCentersByTeacher(@Param("teacherId") UUID teacherId);
}
