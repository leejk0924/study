package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class test {
    @ResponseBody
    @PostMapping("/log-test-test")
    public HelloData logTest(@RequestBody HelloData data) {

        log.info("username log={}", data.getUsername());
        log.info("username log=" + data.getUsername());
        log.info("username log={}, age log={}", data.getUsername(), data.getAge());
        log.info("username log=" + data.getUsername() + ", age log=" + data.getAge());

        return data;
    }
}


