package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/api/dashboard")
@Tag(name = "Member", description = "맴버 API")
public interface MemberController {

    @Operation(
            summary = "로그인 API",
            description = "테스트 API."
    )
    @GetMapping("/")
    ApiResponse<Void> getTest();

}
