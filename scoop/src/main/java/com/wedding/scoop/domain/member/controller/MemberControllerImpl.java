package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.member.dto.request.PostLoginRequest;
import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.GetNicknameResponse;
import com.wedding.scoop.domain.member.service.MemberService;
import com.wedding.scoop.domain.member.service.NicknameService;
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
    private final NicknameService nicknameService;

    ///  GET /v1/api/member/nickname
    @Override
    public ApiResponse<GetNicknameResponse> generateNickname() {
        log.info("nickname request");

        return ApiResponse.success(
                nicknameService.generateNickname(),
                "check duplication success"
        );
    }

    ///  POST /v1/api/member/login
    @Override
    public ApiResponse<Void> login(PostLoginRequest postLoginRequest,
                                   BindingResult bindingResult,
                                   HttpServletResponse response) {
        log.info("member login request");

        String jwtToken = memberService.login(
                postLoginRequest.getUserId()
        );

        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);

        return ApiResponse.success("login success");
    }

    ///  POST /v1/api/member/signIn
    @Override
    public ApiResponse<Void> signIn(
            PostSignInRequest postSignInRequest,
            BindingResult bindingResult,
            HttpServletResponse response) {
        log.info("member signIn request");


        String jwtToken = memberService.signIn(postSignInRequest);

        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);

        return ApiResponse.success("sign in success");
    }


}
