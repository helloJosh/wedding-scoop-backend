package com.wedding.scoop.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
