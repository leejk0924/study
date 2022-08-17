package com.example.security.controller;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // localhost:8080
    // localhost:8080/
    @GetMapping({"", "/"})
    public String index() {
        // 머스테치 기본 폴더 src/main/resources/
        // 뷰리졸버 설정 : templates (prefix), .mustache(suffix) 생략가능 (의존성 주입되었다면)
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    // 스프링 시큐리티 해당주소를 가져간다.  - securityConfig 파일 생성 후 작동 안함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);  // 회원가입 잘됨, 비밀번호가 평문으로 입력 => 시큐리티로 로그인 할 수 없다. 이유는 패스워드가 암호화 되어있지 않다.

        return "redirect:/loginForm";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")   // 여러개 조건 걸 경우
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "데이터 정보";
    }
}

