package com.example.talimet.eduCenter.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "edu_center")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EduCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,unique = true)
    private String name;
    private String address;
    private String phoneNumber;

}
