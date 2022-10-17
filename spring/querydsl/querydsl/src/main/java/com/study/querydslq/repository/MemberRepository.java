package com.study.querydslq.repository;

import com.study.querydslq.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, QuerydslPredicateExecutor {
    // select m from Member m where m.username=?
    List<Member> findByUsername(String username);
}
