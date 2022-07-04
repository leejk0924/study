package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan   // 스프링이 해당 클래스의 패키지포함, 하위 패키지를 전부 검색하여 서블릿을 자동으로 등록
@SpringBootApplication
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

    // 이 코드를 application.properties 에서 설정해줌 (스프링 부트가 다 해준다.)
//    @Bean
//    InternalResourceViewResolver internalResourceViewResolver() {
//        return new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
//    }
}
