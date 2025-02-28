package com.wedding.scoop.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final WebClient webClient;

    public Map<String, Object> getOauthAccessToken(String authorizationCode, String provider, String redirectUri) {
        if (provider.equals("kakao")) {
            String tokenUri = getTokenUri("kakao");
            String clientId = "YOUR_CLIENT_ID";

            return webClient.post()
                    .uri(tokenUri)
                    .bodyValue(Map.of(
                            "grant_type", "authorization_code",
                            "client_id", clientId,
                            "redirect_uri", redirectUri,
                            "code", authorizationCode
                    ))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } else if (provider.equals("apple")) {
            String tokenUri = getTokenUri("apple");
            String clientId = "YOUR_CLIENT_ID";

            return webClient.post()
                    .uri(tokenUri)
                    .bodyValue(Map.of(
                            "grant_type", "authorization_code",
                            "client_id", clientId,
                            "redirect_uri", redirectUri,
                            "code", authorizationCode
                    ))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        }

        throw new RuntimeException("auth error oauth");

    }

    public Map<String, Object> getMemberInfo(String oauthAccessToken, String provider) {
        if (provider.equals("kakao")) {
            return webClient.get()
                    .uri("https://kapi.kakao.com/v2/user/me")
                    .header("Authorization", "Bearer " + oauthAccessToken)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block(); // 동기 방식으로 반환
        } else if (provider.equals("apple")) {
            return webClient.get()
                    .uri("https://kapi.kakao.com/v2/user/me")
                    .header("Authorization", "Bearer " + oauthAccessToken)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block(); // 동기 방식으로 반환
        }

        throw new RuntimeException("auth error oauth");

    }

    private String getTokenUri(String provider) {
        switch (provider.toLowerCase()) {
            case "apple":
                return "https://github.com/login/oauth/access_token";
            case "kakao":
                return "https://kauth.kakao.com/oauth/token";
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }
}
