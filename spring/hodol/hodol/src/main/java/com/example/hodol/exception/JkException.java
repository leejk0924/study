package com.example.hodol.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class JkException extends RuntimeException{

    public final Map<String, String> validation = new HashMap<>();

    public JkException(String message) {
        super(message);
    }

    public JkException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);

    }
}
/**
 * 여러개의 예외처리를 해야할 경우 최상위 예외를 만들어 상속하여 사용
 */