package com.wedding.scoop.domain.vendor.entity;

import com.wedding.scoop.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
public class Price {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private Integer price;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private Vendor vendor;
    @ManyToOne
    private Member member;
}
