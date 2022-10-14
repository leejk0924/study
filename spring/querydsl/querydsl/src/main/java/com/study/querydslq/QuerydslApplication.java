package com.study.querydslq;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class QuerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }

//     //JPAQueryFactory bean 에 등록
    // 동시성 문제가 발생하지 않을까? 라는 의문 -> JPAQueryFactory 의 동시성 문제는 EntityManager 에 의존하고 EntityManager는
    // 동시성 문제 없이 트랜잭션 단위로 분리되어 동작하므로
//    @Bean
//    JPAQueryFactory jpaQueryFactory(EntityManager em) {
//        return new JPAQueryFactory(em);
//    }
}
