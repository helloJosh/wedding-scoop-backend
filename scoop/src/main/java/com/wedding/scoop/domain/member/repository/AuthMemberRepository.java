package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Auth;
import com.wedding.scoop.domain.member.entity.AuthMember;
import com.wedding.scoop.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<AuthMember, String> {
    List<AuthMember> findAuthMembersByMember(Member member);
}
