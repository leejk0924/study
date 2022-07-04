package hello.servlet.web.frontcontroller.V3;

import hello.servlet.web.frontcontroller.ModelAndView;

import java.util.Map;

public interface ControllerV3 {

    ModelAndView process(Map<String, String> paramMap);
}
