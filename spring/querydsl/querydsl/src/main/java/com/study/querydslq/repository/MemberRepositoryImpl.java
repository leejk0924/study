package com.study.querydslq.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydslq.dto.MemberSearchCondition;
import com.study.querydslq.dto.MemberTeamDto;
import com.study.querydslq.dto.QMemberTeamDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.study.querydslq.entity.QMember.member;
import static com.study.querydslq.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

// 네이밍 규칙이 있음-> 반드시 적용하고자 하는 레포지토리명 뒤에 Impl 을 붙여줘야한다. (MemberRepository 에 적용하려면 MemberRepositoryImpl)
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .fetch();
    }

    private BooleanExpression usernameEq(String username) {
//        return StringUtils.isEmpty(username) ? null : member.username.eq(username);   // 부정
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
