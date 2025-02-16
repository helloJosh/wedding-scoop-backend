package com.wedding.scoop.domain.vendor.entity;

import com.wedding.scoop.domain.member.entity.Member;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String title;

    @Lob // text b 저장
    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Boolean active = Boolean.TRUE;

    @ManyToOne
    private Member member;

    @OneToOne
    private Vendor vendor;

}
