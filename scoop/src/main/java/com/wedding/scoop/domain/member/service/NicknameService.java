package com.wedding.scoop.domain.member.service;

import com.wedding.scoop.domain.member.dto.response.GetNicknameResponse;
import com.wedding.scoop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NicknameService {
    private final List<String> adjectives = List.of(
            "차분한", "조용한", "예쁜", "설레는", "빛나는", "따뜻한", "사랑스러운", "포근한",
            "귀여운", "햇살 같은", "노을진", "맑은", "고요한", "은은한", "눈부신", "달콤한",
            "로맨틱한", "감미로운", "우아한", "청순한", "반짝이는", "화사한", "싱그러운", "순수한",
            "행복한", "잔잔한", "은빛의", "햇살 머금은", "설렘 가득한", "사랑 가득한", "영롱한"
    );

    private final List<String> nouns = List.of(
            "신부", "신랑", "부케", "예식장", "드레스", "베일", "청첩장", "하객", "예물",
            "웨딩홀", "사랑", "반지", "순간", "약속", "연인", "꽃길", "축가", "하늘", "별빛",
            "달님", "햇살", "설렘", "고백", "추억", "첫눈", "입맞춤", "마음", "포옹", "순정",
            "백합", "장미", "루비", "웨딩카", "샴페인", "촛불", "하루", "동화", "꿈"
    );

    private final MemberRepository memberRepository;

    public GetNicknameResponse generateNickname() {
        return GetNicknameResponse.builder()
            .valid(Boolean.TRUE)
            .recommendedName(generateWeddingNickname())
            .build();
    }

    public String generateWeddingNickname() {
        String adj = adjectives.get((int) (Math.random() * adjectives.size()));
        String noun = nouns.get((int) (Math.random() * nouns.size()));
        return adj + " " + noun;
    }

}
