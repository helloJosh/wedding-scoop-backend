package com.wedding.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Auth {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String type;
}
