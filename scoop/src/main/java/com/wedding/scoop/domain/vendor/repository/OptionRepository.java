package com.wedding.scoop.domain.vendor.repository;

import com.wedding.scoop.domain.vendor.entity.Category;
import com.wedding.scoop.domain.vendor.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, String> {
    Optional<Option> findByName(String name);
}
