package com.wedding.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class AuthMember {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Auth auth;
}
