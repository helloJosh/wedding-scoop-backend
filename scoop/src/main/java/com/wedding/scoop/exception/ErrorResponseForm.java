package com.wedding.scoop.exception;

import lombok.Builder;

@Builder
public record ErrorResponseForm(String title, int status, String timestamp) {
}