package com.example.talimet.lessonDays.entity;


import com.example.talimet.common.enums.DaysOfWeek;
import com.example.talimet.group.entity.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "lesson_days")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDays {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private DaysOfWeek day;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private Group group;
}
