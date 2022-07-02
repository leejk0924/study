package hello.servlet.web.frontcontroller.V3_practice;

import hello.servlet.web.frontcontroller.ModelView2;
import hello.servlet.web.frontcontroller.MyView_practice;
import hello.servlet.web.frontcontroller.V3_practice.controller.MemberFormControllerV3_practice;
import hello.servlet.web.frontcontroller.V3_practice.controller.MemberListControllerV3_practice;
import hello.servlet.web.frontcontroller.V3_practice.controller.MemberSaveControllerV3_practice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletsV3_practice", urlPatterns ="/from-controller/v3/*")
public class FrontControllerServletsV3_practice extends HttpServlet {

    private Map<String, ControllerV3_practice> controllerMap = new HashMap<>();

    public FrontControllerServletsV3_practice() {
        controllerMap.put("/from-controller/v3/members/new-form", new MemberFormControllerV3_practice());
        controllerMap.put("/from-controller/v3/members/save", new MemberSaveControllerV3_practice());
        controllerMap.put("/from-controller/v3/members", new MemberListControllerV3_practice());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3_practice controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap2(request);


        ModelView2 mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView_practice view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);


    }

    private MyView_practice viewResolver(String viewName) {
        return new MyView_practice("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap2(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
