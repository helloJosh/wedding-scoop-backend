package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.Vendor;
import com.wedding.scoop.domain.vendor.entity.VendorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorTypeRepository extends JpaRepository<VendorType, String> {
    List<VendorType> findAllByVendor(Vendor vendor);
}
