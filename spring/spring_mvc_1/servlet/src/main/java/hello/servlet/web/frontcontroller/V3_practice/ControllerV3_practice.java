package hello.servlet.web.frontcontroller.V3_practice;


import hello.servlet.web.frontcontroller.ModelView_practice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Map;


public interface ControllerV3_practice  {
    ModelView_practice process(Map<String, String> paramMap);

}
