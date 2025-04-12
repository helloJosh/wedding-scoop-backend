package com.wedding.scoop.domain.vendor.entity;

import com.wedding.scoop.domain.vendor.entity.enums.VendorTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String name;
    private String url;

    @Column(length = 20)
    private String phoneNumber;

    private String kakaoUrl;
    private String igUrl;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    private VendorTypes vendorTypes;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Boolean active = Boolean.TRUE;

}
