package com.wedding.scoop.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetNicknameResponse {
    Boolean valid;
    String recommendedName;
}
