package com.example.hodol.controller;

import com.example.hodol.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public String post(@RequestBody PostCreate params) {
        log.info("params={}", params);
        return "post : Hello World";
    }
}
