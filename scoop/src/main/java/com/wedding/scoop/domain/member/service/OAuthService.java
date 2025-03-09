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
        String clientId = "4edaa8c9db64c1fd74b9f013e78d3eec";

        return kakaoOAuthFeignClient.getAccessToken("authorization_code", clientId, redirectUri, authorizationCode);
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String oauthAccessToken) {
        return kakaoOAuthFeignClient.getUserInfo(oauthAccessToken);
    }

    public AppleTokenResponse getAppleOauthAccessToken(String authorizationCode, String redirectUri) {
        String clientId = "001624.1f156f5e36d94a169b0c426e8b414488.0122";

        return appleOAuthFeignClient.getAccessToken("authorization_code", clientId, redirectUri, authorizationCode);
    }

    public Map<String, Object> getAppleUserInfo(String oauthAccessToken) {
        return appleOAuthFeignClient.getUserInfo(oauthAccessToken);
    }


}
