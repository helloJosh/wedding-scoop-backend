package com.wedding.scoop.domain.member.repository;

import com.wedding.scoop.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
