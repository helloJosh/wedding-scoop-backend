package com.wedding.auth.entity;

import com.wedding.auth.entity.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @UuidGenerator
    @GeneratedValue
    private String id;

    @Column(length = 10)
    private String name;

    private String email;
    private Integer age;

    private Role role;
    private LocalDateTime expectedWeddingDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime latestLoginAt;
    private Boolean active = Boolean.TRUE;

    @ManyToOne
    private Oauth oauth;
}
