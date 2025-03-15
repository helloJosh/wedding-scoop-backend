package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.entity.Oauth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUuidAndOauth(String uuid, Oauth oauth);

    @Query("SELECT m.nickname FROM Member m")
    List<String> findAllNicknames();
}
