package com.eduportal.entities;

import com.eduportal.enums.Role;
import com.eduportal.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Role roles;
    private Status status = Status.ACTIVE;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "craeted_at")
    private Instant createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();
}
