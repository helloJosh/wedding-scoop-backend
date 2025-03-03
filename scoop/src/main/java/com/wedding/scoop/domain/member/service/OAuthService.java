package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.adapter.AppleOAuthFeignClient;
import com.wedding.scoop.domain.member.adapter.KakaoOAuthFeignClient;
import com.wedding.scoop.domain.member.dto.response.AppleTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoTokenResponse;
import com.wedding.scoop.domain.member.dto.response.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final KakaoOAuthFeignClient kakaoOAuthFeignClient;
    private final AppleOAuthFeignClient appleOAuthFeignClient;

    public KakaoTokenResponse getKakaoOauthAccessToken(String authorizationCode, String redirectUri) {
        String clientId = "YOUR_CLIENT_ID";

        return kakaoOAuthFeignClient.getAccessToken("authorization_code", clientId, redirectUri, authorizationCode);
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String oauthAccessToken) {
        return kakaoOAuthFeignClient.getUserInfo(oauthAccessToken);
    }

    public AppleTokenResponse getAppleOauthAccessToken(String authorizationCode, String redirectUri) {
        String clientId = "YOUR_CLIENT_ID";

        return appleOAuthFeignClient.getAccessToken("authorization_code", clientId, redirectUri, authorizationCode);
    }

    public Map<String, Object> getAppleUserInfo(String oauthAccessToken) {
        return appleOAuthFeignClient.getUserInfo(oauthAccessToken);
    }


}
