package hello.servlet.web.frontcontroller.V3_practice;

import hello.servlet.web.frontcontroller.ModelView2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Map;


public interface ControllerV3_practice  {
    ModelView2 process(Map<String, String> paramMap);

}
