package com.wedding.scoop.support;

import com.wedding.scoop.exception.JwtExpiredException;
import com.wedding.scoop.exception.JwtNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {
    private final SecretKey accessTokenKey = Keys.hmacShaKeyFor("test-secure-key-for-access-token".getBytes());
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 1; //1시간

    // JWT 토큰 생성
    public String generateAccessToken(String loginId, List<String> roles) {
        String accessToken = Jwts.builder()
                .subject(loginId)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(accessTokenKey)
                .compact();

        log.info("AccessToken : {}", accessToken);

        return  accessToken;
    }

    public Boolean validateAccessToken(String accessToken){
        try {
            Jwts.parser()
                    .verifyWith(accessTokenKey).build()
                    .parseSignedClaims(accessToken)
                    .getPayload()
                    .getSubject();

            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token Expired");
        } catch (JwtException e) {
            throw new JwtNotValidException("Token Not Valid");
        }
    }

    public String getLoginId(String accessToken){
        try {
            return Jwts.parser()
                    .verifyWith(accessTokenKey).build()
                    .parseSignedClaims(accessToken)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token Expired");
        } catch (JwtException e) {
            throw new JwtNotValidException("Token Not Valid");
        }
    }

    public List<SimpleGrantedAuthority> getRole(String accessToken){
        try {
            List<String> roles = (List<String>) Jwts.parser()
                    .verifyWith(accessTokenKey).build()
                    .parseSignedClaims(accessToken)
                    .getPayload()
                    .get("roles");

            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token Expired");
        } catch (JwtException e) {
            throw new JwtNotValidException("Token Not Valid");
        }
    }
}
