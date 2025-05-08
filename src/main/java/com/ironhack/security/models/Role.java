package com.ironhack.security.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Enumerated indicates to JPA that the value will be stored as a string
    @Column(columnDefinition = "VARCHAR(30)")
    private ERole name;
}
