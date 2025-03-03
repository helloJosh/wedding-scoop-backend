package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
    Optional<Auth> findAuthByType(String type);
}
