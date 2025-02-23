package com.wedding.scoop.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetValidationResponse {
    Boolean valid;
    String recommendedName;
}
