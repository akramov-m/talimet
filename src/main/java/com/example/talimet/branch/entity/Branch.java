package com.example.talimet.branch.entity;


import com.example.talimet.eduCenter.entity.EduCenter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "branches")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String branchName;
    private String branchAddress;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private EduCenter eduCenter;
}
