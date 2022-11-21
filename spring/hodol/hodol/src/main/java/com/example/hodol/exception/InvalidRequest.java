package com.example.hodol.exception;

import lombok.Getter;

/**
 * status -> 400
 */
@Getter
public class InvalidRequest extends JkException{
    private static final String MESSAGE = "잘못된 요청입니다.";
    private String fieldName;
    private String message;

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(Throwable cause) {
        super(MESSAGE, cause);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    public int getStatusCode() {
        return 400;
    }
}
/**
 * getStatusCode 메서드를 만들어서 해당 예외의 상태코드를 넘겨준다.
 */