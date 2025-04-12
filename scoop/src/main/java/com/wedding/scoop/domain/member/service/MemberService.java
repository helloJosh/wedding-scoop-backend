package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.AppleTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoUserInfoResponse;
import com.wedding.scoop.domain.member.entity.Auth;
import com.wedding.scoop.domain.member.entity.AuthMember;
import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.entity.Oauth;
import com.wedding.scoop.domain.member.entity.enums.AuthType;
import com.wedding.scoop.domain.member.entity.enums.Provider;
import com.wedding.scoop.domain.member.repository.AuthMemberRepository;
import com.wedding.scoop.domain.member.repository.AuthRepository;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import com.wedding.scoop.domain.member.repository.OauthRepository;
import com.wedding.scoop.exception.conflict.MemberAlreadyExistException;
import com.wedding.scoop.exception.notfound.MemberNotFoundException;
import com.wedding.scoop.exception.notfound.OAuthNotFoundException;
import com.wedding.scoop.support.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final OauthRepository oauthRepository;
    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;
    private final AuthMemberRepository authMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String userId) {
        Member member = memberRepository.findByUuid(userId)
                .orElseThrow(() ->
                        new MemberNotFoundException("Member Not Found :" + userId)
                );

        return jwtTokenProvider.generateAccessToken(String.valueOf(member.getId()));
    }

    public String signIn(PostSignInRequest postSignInRequest){
        Oauth oauth = oauthRepository.findOauthByProvider(postSignInRequest.getProvider())
                .orElseGet(() -> {
                    Oauth newOauth = new Oauth(postSignInRequest.getProvider());
                    oauthRepository.saveAndFlush(newOauth);
                    return newOauth;
                });

        Auth auth = authRepository.findAuthByType(AuthType.MEMBER)
                .orElseGet(() -> {
                    Auth newAuth = new Auth(AuthType.MEMBER);
                    authRepository.saveAndFlush(newAuth);
                    return newAuth;
                });

        memberRepository.findByUuid(postSignInRequest.getUserId())
                .ifPresent(m -> {
                    throw new MemberAlreadyExistException("Id duplicated : " + postSignInRequest.getUserId());
                });

        Member newMember = new Member(postSignInRequest.getUserId(), oauth);
        AuthMember authMember = new AuthMember(newMember, auth);

        memberRepository.save(newMember);
        authMemberRepository.save(authMember);

        return jwtTokenProvider.generateAccessToken(String.valueOf(newMember.getId()));
    }
}
