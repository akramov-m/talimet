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
@Table(name = "teacher_enrollments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "teacher_id",unique = true)
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;
}
