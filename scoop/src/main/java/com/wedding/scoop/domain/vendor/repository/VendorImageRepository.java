package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.Vendor;
import com.wedding.scoop.domain.vendor.entity.VendorImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorImageRepository extends JpaRepository<VendorImage, String> {
    Optional<VendorImage> findByVendorAndType(Vendor vendor, String type);
}
