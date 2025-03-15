package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.member.dto.request.PostLoginRequest;
import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.GetValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/api/member")
@Tag(name = "Member", description = "권한 없이 접근 가능한 맴버 API")
public interface MemberController {
    @Operation(
            summary = "별명 중복확인 API",
            description = "요청한 별명을 중복 검사후 추천 이름 반환"
    )
    @GetMapping("/validation")
    ApiResponse<GetValidationResponse> duplicationCheck(@RequestParam("nickname") String nickname);

    @Operation(
            summary = "로그인 API",
            description = "Oauth 발급 UserId를 가지고 헤더를 통해 JWT 토큰 반환"
    )
    @PostMapping("/login")
    ApiResponse<Void> login(@Valid @RequestBody PostLoginRequest postLoginRequest,
                            BindingResult bindingResult,
                            HttpServletResponse response);

    @Operation(
            summary = "회원가입 API",
            description = "연령, 별명을 받아서 회원가입 완료"
    )
    @PostMapping("/signIn")
    ApiResponse<Void> signIn(@Valid @RequestBody PostSignInRequest postSignInRequest,
                             BindingResult bindingResult,
                             HttpServletResponse response);
}