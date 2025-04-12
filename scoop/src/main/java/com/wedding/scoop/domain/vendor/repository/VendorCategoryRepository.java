package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.VendorCategory;
import com.wedding.scoop.domain.vendor.entity.VendorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorCategoryRepository extends JpaRepository<VendorCategory, String> {
}
