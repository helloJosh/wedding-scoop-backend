package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.Category;
import com.wedding.scoop.domain.vendor.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);
}
