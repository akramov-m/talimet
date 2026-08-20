package com.example.talimet.groupLesson.repository;

import com.example.talimet.groupLesson.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GroupLessonRepository extends JpaRepository<Lesson, UUID> {

    @Query("""
    SELECT gs FROM Lesson gs
    WHERE gs.group.id=:groupId
    ORDER BY gs.lessonDate DESC
    """)
    List<Lesson> getLessonsByGroupId(@Param("groupId") UUID groupId);
}
