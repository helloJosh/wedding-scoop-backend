package com.wedding.scoop.domain.vendor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VendorImage {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vendor vendor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    private String type;

    public VendorImage(Vendor vendor, Image image, String type) {
        this.vendor = vendor;
        this.image = image;
        this.type = type;
    }
}
