package com.wedding.auth.entity;

import com.wedding.auth.entity.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class Marriage {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    @OneToOne
    private Member groom;

    @OneToOne
    private Member bride;

}
