package com.wedding.scoop.domain.member.adapter;

import com.wedding.scoop.domain.member.dto.response.KakaoTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoOAuthClient", url = "https://")
public interface KakaoOAuthFeignClient {
    @PostMapping(value = "kauth.kakao.com/oauth/token", consumes = "application/x-www-form-urlencoded")
    KakaoTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String authorizationCode
    );

    @GetMapping("kapi.kakao.com/v2/user/me")
    KakaoUserInfoResponse getUserInfo(@RequestHeader("Authorization") String authorization);
}
