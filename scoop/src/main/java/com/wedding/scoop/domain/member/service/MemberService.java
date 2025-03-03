package com.wedding.scoop.domain.member.service;

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
    private final OAuthService oAuthService;

    public String loginWithSignIn(String oauthCode,String redirectUri, String provider) {
        if (provider.equals("kakao")) {
            KakaoTokenResponse accessTokenInfo = oAuthService.getKakaoOauthAccessToken(oauthCode, redirectUri);
            KakaoUserInfoResponse memberInfo = oAuthService.getKakaoUserInfo(accessTokenInfo.getAccessToken());

            String name = memberInfo.getKakaoAccount().getName();
            String email = memberInfo.getKakaoAccount().getEmail();
            String uuid = memberInfo.getId().toString();

            //uuid 아이디 찾기 맴버 찾고 회원가입 혹은 바로 로그인
            Oauth oauth = new Oauth(Provider.valueOf(provider));
            Member member = memberRepository.findByUuidAndOauth(uuid, oauth)
                    .orElse( new Member(name, email, oauth));
            member.setLatestLoginAt(LocalDateTime.now());

            Auth auth = authRepository.findAuthByType("member")
                    .orElse(new Auth("member"));
            AuthMember authMember = new AuthMember(member, auth);

            memberRepository.save(member);
            authRepository.save(auth);
            oauthRepository.save(oauth);
            authMemberRepository.save(authMember);

            return jwtTokenProvider.generateAccessToken(member.getId());
        } else if (provider.equals("apple")) {

            AppleTokenResponse accessTokenInfo = oAuthService.getAppleOauthAccessToken(oauthCode, redirectUri);

            Map<String, Object> memberInfo = oAuthService.getAppleUserInfo(accessTokenInfo.getAccessToken());

            String name = null;
            String email = null;
            String uuid = (String) memberInfo.get("sub");

            //uuid 아이디 찾기 맴버 찾고 회원가입 혹은 바로 로그인
            Oauth oauth = new Oauth(Provider.valueOf(provider));
            Member member = memberRepository.findByUuidAndOauth(uuid, oauth)
                    .orElse( new Member(name, email, oauth));
            member.setLatestLoginAt(LocalDateTime.now());

            Auth auth = authRepository.findAuthByType("member")
                    .orElse(new Auth("member"));
            AuthMember authMember = new AuthMember(member, auth);

            memberRepository.save(member);
            authRepository.save(auth);
            oauthRepository.save(oauth);
            authMemberRepository.save(authMember);

            return jwtTokenProvider.generateAccessToken(member.getId());
        }
        return null;
    }
}
