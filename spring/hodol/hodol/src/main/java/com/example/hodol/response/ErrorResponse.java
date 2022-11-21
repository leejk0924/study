package com.example.hodol.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
// null 이거나 빈값은 아래 어노테이션으로 뺄 수 있음
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private String code;
    private String message;

    private final Map<String, String> validation;

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }
}
//TODO
// Validation 항목 만들어서 title, content 만들기
