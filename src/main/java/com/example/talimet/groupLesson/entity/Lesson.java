package com.example.talimet.groupLesson.entity;

import com.example.talimet.group.entity.Group;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "group_lesson")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String lessonDate;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
