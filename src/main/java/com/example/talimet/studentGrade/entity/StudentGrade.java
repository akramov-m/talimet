package com.example.talimet.studentGrade.entity;

import com.example.talimet.studentEnrollment.entity.StudentEnrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "student_grade")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
