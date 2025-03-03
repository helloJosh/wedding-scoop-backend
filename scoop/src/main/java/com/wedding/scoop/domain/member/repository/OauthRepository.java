package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<Oauth, String> {
}
