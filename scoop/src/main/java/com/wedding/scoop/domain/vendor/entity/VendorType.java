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
@AllArgsConstructor
@NoArgsConstructor
public class VendorType {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String detailName;
    private Double price;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Boolean active = Boolean.TRUE;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vendor vendor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Type type;
}
