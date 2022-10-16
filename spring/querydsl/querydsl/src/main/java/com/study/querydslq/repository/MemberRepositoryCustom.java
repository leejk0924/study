package com.study.querydslq.repository;

import com.study.querydslq.dto.MemberSearchCondition;
import com.study.querydslq.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);

}
