package com.example.talimet.message.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "messages")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String senderFullName;
    private String responderFullName;
    private String subject;
    private String question;
    private String answer;
    private String phoneNumber;
    private LocalDateTime sendTime;
}
