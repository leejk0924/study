package hello.servlet.web.frontcontroller.V3_practice.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView2;
import hello.servlet.web.frontcontroller.V3_practice.ControllerV3_practice;

import java.util.Map;

public class MemberSaveControllerV3_practice implements ControllerV3_practice {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView2 process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView2 mv2 = new ModelView2("save-result");
        mv2.getModel().put("member", member);

        return mv2;
    }
}
