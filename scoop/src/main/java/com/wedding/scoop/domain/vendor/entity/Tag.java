package com.wedding.scoop.domain.vendor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @Column(length = 50)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
