package com.example.talimet.studentEnrollment.entity;

import com.example.talimet.group.entity.Group;
import com.example.talimet.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "student_enrollments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private LocalDate joinedAt;
}
