package com.example.talimet.message.repository;

import com.example.talimet.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    Optional<Message> findMessageById(UUID messageId);

    @Query("""
    SELECT DISTINCT m
    FROM Message m
    WHERE m.answer IS NULL
    ORDER BY m.sendTime DESC
    """)
    List<Message> getQuestions();


    @Query("""
    SELECT DISTINCT m
    FROM Message m
    WHERE m.answer IS NOT NULL
    ORDER BY m.sendTime DESC
    LIMIT 5
    """)
    List<Message> getLastQuestion();
}
