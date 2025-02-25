package com.wedding.scoop.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final WebClient webClient;

    public Map<String, Object> getAccessToken(String authorizationCode, String provider) {
        String tokenUri = getTokenUri(provider);

        String clientId = "YOUR_CLIENT_ID";
        String clientSecret = "YOUR_CLIENT_SECRET";
        String redirectUri = "YOUR_REDIRECT_URI";

        return webClient.post()
                .uri(tokenUri)
                .bodyValue(Map.of(
                        "grant_type", "authorization_code",
                        "client_id", clientId,
                        "client_secret", clientSecret,
                        "redirect_uri", redirectUri,
                        "code", authorizationCode
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }


    private String getTokenUri(String provider) {
        switch (provider.toLowerCase()) {
            case "google":
                return "https://oauth2.googleapis.com/token";
            case "github":
                return "https://github.com/login/oauth/access_token";
            case "kakao":
                return "https://kauth.kakao.com/oauth/token";
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }

    public Map<String, Object> getUserInfo(String accessToken, String provider) {
        String userInfoUri = getUserInfoUri(provider);

        return webClient.get()
                .uri(userInfoUri)
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    private String getUserInfoUri(String provider) {
        switch (provider.toLowerCase()) {
            case "google":
                return "https://www.googleapis.com/oauth2/v3/userinfo";
            case "github":
                return "https://api.github.com/user";
            case "kakao":
                return "https://kapi.kakao.com/v2/user/me";
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }
}
