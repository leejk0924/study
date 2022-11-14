package com.example.hodol.controller;

import com.example.hodol.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BoardController {
    @GetMapping("/get")
    public String get() {
        return "get : Hello World";
    }
    /*@PostMapping("/post")
        public String post(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "post : Hello World";
    }*/
/*    @PostMapping("/post")
    public String post(@RequestParam Map<String, String> params) {
        log.info("params={}", params);
        return "post : Hello World";
    }*/
    
    // content-type form 일 경우
/*    @PostMapping("/post")
    public String post(@ModelAttribute PostCreate params) {
        log.info("params={}", params);
        return "post : Hello World";
    }*/
    @PostMapping("/post")
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        // 데이터를 검증하는 이유
        // 1. client 개발자가 실수할 수 있음
        // 2. client bug 로 인해 값이 누락될 수 있다.
        // 3. 외부의 나쁜 사람으로인해 값을 임의로 조작해서 보낼 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        // 5. 서버 개발자의 편안함을 위해
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField();
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }
        log.info("params={}", params);
        // 잘못된 값을 보내주고 싶지만 controller로 요청이 오지 않음
        return Map.of();
    }
}
