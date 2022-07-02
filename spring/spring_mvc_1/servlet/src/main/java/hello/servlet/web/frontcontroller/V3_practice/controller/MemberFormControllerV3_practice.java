package hello.servlet.web.frontcontroller.V3_practice.controller;

import hello.servlet.web.frontcontroller.ModelView2;
import hello.servlet.web.frontcontroller.V3_practice.ControllerV3_practice;

import java.util.Map;

public class MemberFormControllerV3_practice implements ControllerV3_practice {
    @Override
    public ModelView2 process(Map<String, String> paramMap) {

        return new ModelView2("new-form");
    }
}
