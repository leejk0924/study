package hello.servlet.web.frontcontroller.V3;



import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;


import hello.servlet.web.frontcontroller.V3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberSaveControllerV3;
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
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletsV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();


    public FrontControllerServletsV3() {
        // 매핑 정보 담기
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FrontControllerServletsV3");

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

//        request.getParameterNames().toString();
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        // 논리 이름 new-form
        String viewName = mv.getViewName(); // new-form

        // 뷰 이름 만들기
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
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
