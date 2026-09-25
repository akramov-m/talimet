package com.example.talimet.teacherEnrollment.entity;

import com.example.talimet.group.entity.Group;
import com.example.talimet.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Table(
        name = "teacher_enrollments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_teacher_group",
                        columnNames = {"teacher_id", "group_id"}
                )
        }
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;
}