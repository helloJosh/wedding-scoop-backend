package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.member.dto.request.PostLoginRequest;
import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.GetValidationResponse;
import com.wedding.scoop.domain.member.service.MemberService;
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

    @Override
    public ApiResponse<GetValidationResponse> duplicationCheck() {

        return ApiResponse.success(new GetValidationResponse(true, "testestsetset"), "check duplication success");
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
    public ApiResponse<Void> signIn(PostSignInRequest postSignInRequest, BindingResult bindingResult) {

        return ApiResponse.success("sign in success");
    }


}
