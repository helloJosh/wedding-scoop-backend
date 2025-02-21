package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.member.dto.request.PostLoginRequest;
import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.GetValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/api/member")
@Tag(name = "Member", description = "권한 없이 접근 가능한 맴버 API")
public interface MemberController {
    @Operation(
            summary = "중복확인 API",
            description = "테스트 API."
    )
    @GetMapping("/validation")
    ApiResponse<GetValidationResponse> duplicationCheck();

    @Operation(
            summary = "로그인 API",
            description = "로그인 요청 API "
    )
    @PostMapping("/login")
    ApiResponse<PostLoginRequest> login();

    @Operation(
            summary = "중복확인 API",
            description = "테스트 API."
    )
    @PostMapping("/signIn")
    ApiResponse<PostSignInRequest> signIn();
}