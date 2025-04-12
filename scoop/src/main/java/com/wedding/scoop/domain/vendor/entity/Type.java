package com.wedding.scoop.domain.vendor.entity;

import com.wedding.scoop.domain.vendor.entity.enums.VendorTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    @UuidGenerator
    @GeneratedValue
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private VendorTypes vendorTypes;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
