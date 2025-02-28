package com.wedding.scoop.filter;

import com.wedding.scoop.domain.member.entity.AuthMember;
import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.repository.AuthMemberRepository;
import com.wedding.scoop.domain.member.repository.AuthRepository;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import com.wedding.scoop.support.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;
    private final AuthMemberRepository authMemberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if (token != null && jwtTokenProvider.validateAccessToken(token)) {
            String memberId = jwtTokenProvider.getMemberId(token);

            Member member = memberRepository.findById(memberId).orElseThrow(
                    () -> new RuntimeException("no member found")
            );

            List<GrantedAuthority> authorities = authMemberRepository.findAuthMembersByMember(member)
                    .stream()
                    .map(am -> new SimpleGrantedAuthority("ROLE_" + am.getAuth().getType()))
                    .collect(Collectors.toList());

            UserDetails userDetails = new User(memberId, "", authorities);

            // Security Context에 인증 정보 저장
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     *
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
