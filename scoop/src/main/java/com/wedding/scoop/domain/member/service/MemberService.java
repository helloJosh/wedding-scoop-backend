package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.AppleTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoUserInfoResponse;
import com.wedding.scoop.domain.member.entity.Auth;
import com.wedding.scoop.domain.member.entity.AuthMember;
import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.entity.Oauth;
import com.wedding.scoop.domain.member.entity.enums.Provider;
import com.wedding.scoop.domain.member.repository.AuthMemberRepository;
import com.wedding.scoop.domain.member.repository.AuthRepository;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import com.wedding.scoop.domain.member.repository.OauthRepository;
import com.wedding.scoop.exception.notfound.MemberNotFoundException;
import com.wedding.scoop.exception.notfound.OAuthNotFoundException;
import com.wedding.scoop.support.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final OauthRepository oauthRepository;
    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;
    private final AuthMemberRepository authMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String userId, Provider provider) {
        Oauth oauth = oauthRepository.findOauthByProvider(provider)
                .orElseThrow(() ->
                    new OAuthNotFoundException("Provider Not Found" + provider)
                );

        Member member = memberRepository.findByUuidAndOauth(userId, oauth)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member Not Found :" + userId)
                );


        Auth auth = authRepository.findAuthByType("member")
                .orElse(new Auth("member"));
        AuthMember authMember = new AuthMember(member, auth);

        authRepository.save(auth);
        oauthRepository.save(oauth);
        authMemberRepository.save(authMember);

        return jwtTokenProvider.generateAccessToken(String.valueOf(member.getId()));
    }

    public String signIn(PostSignInRequest postSignInRequest){
        Oauth oauth = oauthRepository.findOauthByProvider(postSignInRequest.getProvider())
                .orElseGet(() -> {
                    Oauth newOauth = new Oauth(postSignInRequest.getProvider());
                    oauthRepository.save(newOauth);
                    return newOauth;
                });

        Member member = memberRepository.findByUuidAndOauth(postSignInRequest.getUserId(), oauth)
                .orElseGet(() -> {
                    Member newMember = new Member(postSignInRequest.getUserId(), oauth);
                    memberRepository.save(newMember);
                    return newMember;
                });

        member.setName(postSignInRequest.getName());
        member.setAgeRange(postSignInRequest.getAgeRange());

        Auth auth = authRepository.findAuthByType("member")
                .orElse(new Auth("member"));
        AuthMember authMember = new AuthMember(member, auth);

        authMemberRepository.save(authMember);
        memberRepository.save(member);


        return jwtTokenProvider.generateAccessToken(String.valueOf(member.getId()));
    }
}
