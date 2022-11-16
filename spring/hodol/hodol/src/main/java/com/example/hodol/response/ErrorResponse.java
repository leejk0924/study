package com.example.hodol.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 *     "code" : "400",
 *     "message" : "잘못된 요청입니다.",
 *     "validation" : {
 *         "title" : "값을 입력해주세요",
 *
 *     }
 * }
 */
@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;

    private final Map<String, String> validation = new HashMap<>();
    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
//TODO
// Validation 항목 만들어서 title, content 만들기
