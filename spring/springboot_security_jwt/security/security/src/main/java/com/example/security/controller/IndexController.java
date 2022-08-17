package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // localhost:8080
    // localhost:8080/
    @GetMapping({"", "/"})
    public String index() {
        // 머스테치 기본 폴더 src/main/resources/
        // 뷰리졸버 설정 : templates (prefix), .mustache(suffix) 생략가능 (의존성 주입되었다면)
        return "index"; // src/main/resources/templates/index.mustache
    }
}
