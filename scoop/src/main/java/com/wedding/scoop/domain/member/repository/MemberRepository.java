package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.entity.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUuidAndOauth(String uuid, Oauth oauth);
}
