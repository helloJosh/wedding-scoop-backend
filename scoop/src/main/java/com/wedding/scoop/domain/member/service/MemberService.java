package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveOrUpdateUser(Map<String, Object> userInfo, String provider) {
        String oauthId = (String) userInfo.get("sub"); // 구글 기준
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");

        return memberRepository.findById(name).orElseThrow();
    }
}
