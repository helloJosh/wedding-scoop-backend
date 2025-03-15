package com.wedding.scoop.domain.member.dto.request;

import com.wedding.scoop.domain.member.entity.enums.Provider;
import lombok.Data;

@Data
public class PostSignInRequest {
    private String userId;
    private Provider provider;
    private String name;
    private String ageRange;
}
