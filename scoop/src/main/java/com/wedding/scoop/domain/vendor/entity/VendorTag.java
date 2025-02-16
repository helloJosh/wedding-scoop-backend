package com.wedding.scoop.domain.vendor.entity;

import com.wedding.scoop.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class VendorTag {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;



    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Boolean active = Boolean.TRUE;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Member member;
}
