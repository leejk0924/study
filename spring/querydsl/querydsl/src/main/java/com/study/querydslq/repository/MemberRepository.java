package com.study.querydslq.repository;

import com.study.querydslq.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // select m from Member m where m.username=?
    List<Member> findByUsername(String username);
}
