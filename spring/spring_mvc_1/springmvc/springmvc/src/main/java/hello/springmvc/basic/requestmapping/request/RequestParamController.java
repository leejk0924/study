package hello.springmvc.basic.requestmapping.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody   // return 값을 HTTP 응답 메시지에 바로 넣고 반환한다.(RestController 와 같은 기능)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        // 변수명을 요청 파라미터명과 일치시켜주면 v2 보다 더 간단한 코드를 만들 수 있음
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        // 단순 타입(String, int, Integer)이면 @RequestParam 도 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) Integer age) {
        // required=true : 반드시 값이 들어와야한다.(안들어오면 Bad Request 발생 , 값이 null 이기 떄문에)
        // required=false : 값이 안들어와도 실행 가능 (값이 null 로 들어감) (공백 처리 불가)
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = false, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") Integer age) {
        // defaultValue : 파라미터가 안넘어오면 해당 값으로 자동으로 입력
        // 공백도 처리해준다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // MultiValueMap 도 가능 (요청 파라미터의 값이 여러개가 들어온다면 MultiValueMap 사용하는 것이 좋음)
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }


}
