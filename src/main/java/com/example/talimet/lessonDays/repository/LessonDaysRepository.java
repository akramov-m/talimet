package com.example.talimet.lessonDays.repository;

import com.example.talimet.lessonDays.entity.LessonDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LessonDaysRepository extends JpaRepository<LessonDays, UUID> {

    @Query("""
    SELECT DISTINCT ld FROM LessonDays ld
    WHERE ld.group.id=:groupId
    """)
    List<LessonDays> getLessonDaysByGroup(@Param("groupId") UUID groupId);
}
