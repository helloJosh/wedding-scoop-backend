package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Oauth;
import com.wedding.scoop.domain.member.entity.enums.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthRepository extends JpaRepository<Oauth, String> {
    Optional<Oauth> findOauthByProvider(Provider provider);
}
