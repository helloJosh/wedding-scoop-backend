package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.Vendor;
import com.wedding.scoop.domain.vendor.entity.enums.VendorTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, String> {
    Optional<Vendor> findByName(String name);
    List<Vendor> findAllByVendorTypes(VendorTypes vendorTypes);
}
