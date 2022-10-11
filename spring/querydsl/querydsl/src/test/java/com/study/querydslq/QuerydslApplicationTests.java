package com.study.querydslq;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydslq.entity.Hello;
import com.study.querydslq.entity.QHello;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// 테스트 후 값 지울 때 트랜잭셔널 어노테이션 사용
@Transactional  
// 값을 저장하고 싶을 때 commit 어노테이션 사용
//@Commit
class QuerydslApplicationTests {

    @PersistenceContext // 다른 프레임워크로 사용 시 해당 어노테이션 사용
//    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Hello hello = new Hello();
        em.persist(hello);

        // querydsl 사용하려면 JPAQueryFactory 사용을 권장함
        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = QHello.hello;

        Hello result = query
                .selectFrom(qHello)
                .fetchOne();

        assertThat(result).isEqualTo(hello);
        assertThat(result.getId()).isEqualTo(hello.getId());
    }
}
