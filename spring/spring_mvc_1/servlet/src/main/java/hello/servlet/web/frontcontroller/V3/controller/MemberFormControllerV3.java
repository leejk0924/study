package hello.servlet.web.frontcontroller.V3.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelAndView process(Map<String, String> paramMap) {
        // 논리 이름만 넣어주기
        return new ModelAndView("new-form");
    }

}
