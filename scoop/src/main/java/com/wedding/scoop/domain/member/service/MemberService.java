package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.entity.Auth;
import com.wedding.scoop.domain.member.entity.AuthMember;
import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.entity.Oauth;
import com.wedding.scoop.domain.member.repository.AuthMemberRepository;
import com.wedding.scoop.domain.member.repository.AuthRepository;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import com.wedding.scoop.support.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;
    private final AuthMemberRepository authMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthService oAuthService;

    public String loginWithSignIn(String oauthCode,String redirectUri, String provider) {
        Map<String, Object> accessTokenInfo = oAuthService.getOauthAccessToken(oauthCode, provider, redirectUri);
        Map<String, Object> memberInfo = oAuthService.getMemberInfo(
                (String)accessTokenInfo.get("access_token") , provider
        );

        String name = (String) memberInfo.get("name");
        String email = (String) memberInfo.get("email");

        Oauth oauth = new Oauth(provider);
        Member member = new Member(name, email, oauth);
        Auth auth = authRepository.findAuthByType("member")
                .orElse(new Auth("member"));

        AuthMember authMember = new AuthMember(member, auth);

        memberRepository.save(member);
        authRepository.save(auth);
        authMemberRepository.save(authMember);

        return jwtTokenProvider.generateAccessToken(member.getId());
    }
}
