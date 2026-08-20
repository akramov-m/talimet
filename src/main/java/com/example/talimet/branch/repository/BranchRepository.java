package com.example.talimet.branch.repository;

import com.example.talimet.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findById(UUID id);

    @Query("""
      SELECT DISTINCT b
      FROM StudentEnrollment se
      JOIN se.group g
      JOIN g.subject s
      JOIN s.branch b
      WHERE se.student.id=:studentId
      AND b.eduCenter.id = :eduCenterId
  """)
    List<Branch> getBranchesByStudent(@Param("studentId") UUID studentId,@Param("eduCenterId") UUID eduCenterId);

    @Query("""
    SELECT b 
    FROM Branch b
    WHERE b.eduCenter.id = :centerId
    """)
    List<Branch> getAllBranchesByCenterId(@Param("centerId") UUID centerId);

    @Query("""
      SELECT DISTINCT b
      FROM TeacherEnrollment te
      JOIN te.group g
      JOIN g.subject s
      JOIN s.branch b
      WHERE te.teacher.id=:teacherId
      AND b.eduCenter.id = :eduCenterId
  """)
    List<Branch> getAllBranchesByTeacher(@Param("teacherId") UUID teacherId,@Param("eduCenterId") UUID eduCenterId);
}
