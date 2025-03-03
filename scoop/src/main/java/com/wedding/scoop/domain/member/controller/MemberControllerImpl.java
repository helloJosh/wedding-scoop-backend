package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.member.dto.request.PostLoginRequest;
import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.GetValidationResponse;
import com.wedding.scoop.domain.member.entity.Member;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import com.wedding.scoop.domain.member.service.MemberService;
import com.wedding.scoop.domain.member.service.ValidationService;
import com.wedding.scoop.support.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController{
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final ValidationService validationService;

    @Override
    public ApiResponse<GetValidationResponse> duplicationCheck(String nickname) {

        return ApiResponse.success(validationService.validate(nickname), "check duplication success");
    }

    @Override
    public ApiResponse<Void> login(PostLoginRequest postLoginRequest,
                                   BindingResult bindingResult,
                                   HttpServletResponse response) {
        String token = memberService.loginWithSignIn(
                postLoginRequest.getOauthCode(),
                postLoginRequest.getRedirectUri(),
                postLoginRequest.getProvider()
        );

        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return ApiResponse.success("login success");
    }

    @Override
    public ApiResponse<Void> signIn(PostSignInRequest postSignInRequest, BindingResult bindingResult, String jwtToken) {
        String memberId = jwtTokenProvider.getMemberId(jwtToken);
        Member member = memberRepository.findById(memberId).orElseThrow();

        member.setName(postSignInRequest.getName());
        member.setAgeRange(postSignInRequest.getAgeRange());

        memberRepository.save(member);

        return ApiResponse.success("sign in success");
    }


}
