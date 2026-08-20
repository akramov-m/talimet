package com.example.talimet.attendance.entity;

import com.example.talimet.common.enums.AttendanceEnum;
import com.example.talimet.groupLesson.entity.Lesson;
import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "student_attendance")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime date;
    private AttendanceEnum attendance;

    @OneToOne
    @JoinColumn(nullable = false,unique = true)
    private Lesson lesson;
    @ManyToOne
    @JoinColumn(nullable = false,name = "student_id")
    private StudentEnrollment student;
}
