package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.VendorOption;
import com.wedding.scoop.domain.vendor.entity.VendorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorOptionRepository extends JpaRepository<VendorOption, String> {
}
