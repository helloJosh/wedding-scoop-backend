package com.wedding.scoop.domain.member.controller;

import com.wedding.scoop.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController{
    @Override
    public ApiResponse<Void> getTest() {
        log.info("test controller start");

        return ApiResponse.success("Hello World");
    }
}
