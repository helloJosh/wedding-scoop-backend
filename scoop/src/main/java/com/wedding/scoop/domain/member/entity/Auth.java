package com.wedding.scoop.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Auth {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String type;
}
