package hello.servlet.web.frontcontroller.V3_practice.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import hello.servlet.web.frontcontroller.ModelView_practice;
import hello.servlet.web.frontcontroller.V3_practice.ControllerV3_practice;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3_practice implements ControllerV3_practice {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView_practice process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView_practice mv2 = new ModelView_practice("members");
        mv2.getModel().put("members", members);
        return mv2;
    }
}
