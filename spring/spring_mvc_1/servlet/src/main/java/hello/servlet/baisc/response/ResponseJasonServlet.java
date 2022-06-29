package hello.servlet.baisc.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.baisc.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJasonServlet", urlPatterns = "/response-json")
public class ResponseJasonServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json  // JSON 으로 반환할 경우 content-type 을 application/json 으로 지정해야 한다.
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge((20));
        
        // {"username":"kim", "age":20}
        // 객체에서 json 변환
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
