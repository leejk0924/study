package hello.servlet.baisc.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.baisc.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    // 자바 객체로 변환하여 사용하려면 Jackson, Gson 과 같은 JSON 변환 라이브러리를 추가해서 사용해야 한다.
    // 스프링 부트로 spring mvc를 선택하면 기본으로 Jackson 라이브러리(ObjectMapper)를 함께 제공한다.
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);
        
        // 객체 타입으로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.userName = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());
        response.getWriter().write("ok");

    }
}
