package com.wedding.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class Oauth {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
