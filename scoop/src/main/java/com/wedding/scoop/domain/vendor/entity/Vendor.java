package com.wedding.scoop.domain.vendor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class Vendor {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String name;
    private Integer capacity;
    private String url;

    @Column(length = 20)
    private String phoneNumber;

    private String kakaoUrl;
    private String igUrl;

    private Double latitude;
    private Double longitude;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Boolean active = Boolean.TRUE;

}
