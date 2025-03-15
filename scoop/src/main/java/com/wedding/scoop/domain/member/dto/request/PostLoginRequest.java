package com.wedding.scoop.domain.member.dto.request;

import com.wedding.scoop.domain.member.entity.enums.Provider;
import lombok.Data;

@Data
public class PostLoginRequest {
    private String userId;
    private Provider provider;
}
