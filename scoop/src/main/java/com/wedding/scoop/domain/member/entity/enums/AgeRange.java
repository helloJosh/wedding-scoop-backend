package com.wedding.scoop.domain.member.entity.enums;

public enum AgeRange {
    TEENS("10~19"),
    TWENTIES("20~29"),
    THIRTIES("30~39"),
    FORTIES("40~49"),
    FIFTIES("50~59"),
    SIXTIES("60~69"),
    OVER_SEVENTY("70+");

    private final String displayName;

    AgeRange(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
