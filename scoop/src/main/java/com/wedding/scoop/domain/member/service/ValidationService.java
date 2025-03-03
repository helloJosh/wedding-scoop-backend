package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.dto.response.GetValidationResponse;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final MemberRepository memberRepository;

    public GetValidationResponse validate(String nickname) {
        List<String> nicknames = memberRepository.findAllNicknames();

        if (nicknames.contains(nickname)) {
            String recommendedName = UUID.randomUUID().toString();

            return GetValidationResponse.builder()
                    .valid(Boolean.FALSE)
                    .recommendedName(recommendedName)
                    .build();
        } else {
            return GetValidationResponse.builder()
                .valid(Boolean.TRUE)
                .recommendedName(nickname)
                .build();
        }
    }

}
