package hello.servlet.web.frontcontroller.V3_practice.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView_practice;
import hello.servlet.web.frontcontroller.V3_practice.ControllerV3_practice;

import java.util.Map;

public class MemberSaveControllerV3_practice implements ControllerV3_practice {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView_practice process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView_practice mv2 = new ModelView_practice("save-result");
        mv2.getModel().put("member", member);

        return mv2;
    }
}
