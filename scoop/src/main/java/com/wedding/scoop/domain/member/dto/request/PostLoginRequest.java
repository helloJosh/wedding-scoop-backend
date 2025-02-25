package com.wedding.scoop.domain.member.dto.request;

import lombok.Data;

@Data
public class PostLoginRequest {
    private String oauthCode;
    private String provider;
}
