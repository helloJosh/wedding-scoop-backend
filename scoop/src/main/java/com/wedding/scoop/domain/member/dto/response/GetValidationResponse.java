package com.wedding.scoop.domain.member.dto.response;

import lombok.Data;

@Data
public class GetValidationResponse {
    Boolean valid;
    String recommendedName;
}
