package com.assemblyvoting.assemblyvoting.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_VOTER")
@Data
public class VoterModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String voterFirstName;

    @Column(nullable = false, length = 70)
    private String voterLastName;

    @Column(nullable = false, unique = true, length = 14)
    private String voterCPF;

    @Column(nullable = false)
    private LocalDateTime registrationDate;
}
