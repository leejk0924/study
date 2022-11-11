package com.example.hodol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @GetMapping("/posts")
    public String get() {
        return "Hello World";
    }
}
