package com.example.hodol.controller;

import com.example.hodol.request.PostCreate;
import com.example.hodol.service.BoardService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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
    public Map<String, String> post(@RequestBody @Valid PostCreate request) throws Exception {
        boardService.write(request);
        // 잘못된 값을 보내주고 싶지만 controller 로 요청이 오지 않음
        return Map.of();
    }
}
