package com.study.querydslq.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {
    // 회원명, 팀명, 나이(ageGoe, ageLoe)
    private String username;
    private String teamName;
    private Integer ageGeo;
    private Integer ageLoe;
}
