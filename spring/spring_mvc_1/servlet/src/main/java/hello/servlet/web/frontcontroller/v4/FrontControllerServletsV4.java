package hello.servlet.web.frontcontroller.v4;


import hello.servlet.web.frontcontroller.MyView;

import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// /v1 하위의 어떤 url 이 들어와도 일단 해당 서블릿이 호출된다.
@Slf4j
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletsV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();


    public FrontControllerServletsV4() {
        // 매핑 정보 담기
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FrontControllerServletsV4");

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

//        request.getParameterNames().toString();
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();            // 추가
        String viewName = controller.process(paramMap, model);

        // 뷰 이름 만들기
        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    // 물리 주소 반환
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }


}
