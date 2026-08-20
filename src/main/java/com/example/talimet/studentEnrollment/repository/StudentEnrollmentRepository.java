package com.example.talimet.studentEnrollment.repository;

import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import com.example.talimet.studentEnrollment.repository.interfaces.StudentProjection;
import com.example.talimet.user.dto.response.UserResponseDto;
import com.example.talimet.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, UUID> {


    @Query("""
    SELECT DISTINCT se.student
    FROM StudentEnrollment se
    WHERE se.group.id=:groupId
    """)
    List<User> getStudentEnrollmentsByGroup(@Param("groupId") UUID groupId);

    @Query("""
    SELECT DISTINCT se
    FROM StudentEnrollment se
    WHERE se.student.id =:studentId
    """)
    Optional<StudentEnrollment> getStudentById(@Param("studentId") UUID studentId);

    @Query("""
    SELECT COUNT(DISTINCT se.student.id)
    FROM StudentEnrollment se
    """)
    Long countOfStudents();

    @Query("""
    SELECT DISTINCT se
    FROM StudentEnrollment se
    JOIN se.group g
    JOIN g.subject s
    WHERE s.branch.id = :branchId
    """)
    List<StudentEnrollment> getStudentsByBranch(@Param("branchId") UUID branchId);


    @Query("""
    SELECT DISTINCT 
        se.student.id AS studentId,
        se.student.firstName AS studentFirstName,
        se.student.lastName AS studentLastName,
        se.student.username AS studentUsername
    FROM StudentEnrollment se
    WHERE se.group.id = :groupId
    """)
    List<StudentProjection> getStudentsProjection(@Param("groupId") UUID groupId);

}
