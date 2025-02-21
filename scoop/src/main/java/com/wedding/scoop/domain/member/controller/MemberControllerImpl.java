package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import com.wedding.scoop.domain.member.dto.request.PostLoginRequest;
import com.wedding.scoop.domain.member.dto.request.PostSignInRequest;
import com.wedding.scoop.domain.member.dto.response.GetValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController{
    @Override
    public ApiResponse<GetValidationResponse> duplicationCheck() {
        GetValidationResponse response = new GetValidationResponse(true, "testestsetset");
        return ApiResponse.success(response, "check duplication success");
    }

    @Override
    public ApiResponse<PostLoginRequest> login() {
        return ApiResponse.success("login success");
    }

    @Override
    public ApiResponse<PostSignInRequest> signIn() {
        return ApiResponse.success("sign in success");
    }
}
