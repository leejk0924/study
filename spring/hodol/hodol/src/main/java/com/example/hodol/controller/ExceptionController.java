package com.example.hodol.controller;

import com.example.hodol.exception.InvalidRequest;
import com.example.hodol.exception.JkException;
import com.example.hodol.response.ErrorResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@NoArgsConstructor
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        // MethodArgumentNotValidException
        ErrorResponse response = ErrorResponse
                .builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }

    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JkException.class)
    public ResponseEntity<ErrorResponse> jkException(JkException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

//        if (e instanceof InvalidRequest) {
//            InvalidRequest invalidRequest = (InvalidRequest) e;
//            String fieldName = invalidRequest.getFieldName();
//            String message = invalidRequest.getMessage();
//            body.addValidation(fieldName, message);
//        }
        // 응답 json validation -> title : 제목에 바보를 포함할 수 없습니다.
        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;
    }

//    @ResponseBody
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorResponse> NPE(NullPointerException e) {
//        ErrorResponse body = ErrorResponse.builder()
//                .code("400")
//                .message(e.getMessage())
//                .build();
//        ResponseEntity<ErrorResponse> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//        return response;
//    }
}
/**
 * 예외로부터 상태코드를 받고, ResponseEntity 를 통해서 상태코드와 에러 메시지를 반환
 */