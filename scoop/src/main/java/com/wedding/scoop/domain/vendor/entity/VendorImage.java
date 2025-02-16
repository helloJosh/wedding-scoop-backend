package com.wedding.scoop.domain.vendor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class VendorImage {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Image image;
}
