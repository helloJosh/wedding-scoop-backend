package com.wedding.scoop.domain.member.adapter;


import com.wedding.scoop.domain.member.dto.response.AppleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "appleOAuthClient", url = "https://appleid.apple.com")
public interface AppleOAuthFeignClient {

    @PostMapping(value = "/auth/token", consumes = "application/x-www-form-urlencoded")
    AppleTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String authorizationCode
    );

    @GetMapping("/auth/userinfo")
    Map<String, Object> getUserInfo(@RequestHeader("Authorization") String authorization);
}
