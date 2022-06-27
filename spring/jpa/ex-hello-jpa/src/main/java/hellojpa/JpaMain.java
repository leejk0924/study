package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // META-INF/persistence.xml 의 persistenceUnitName 값을 받는다.
        // EntityManagerFactory 는 애플리케이션 로딩시점에 딱 한 개만 만들어야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // DB에 저장하는 트랜잭션 단위로 행동할 때마다 EntityManger를 만들어야 한다. (고객의 요청 당 1개 - 쓰레드간 공유 X)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 정석적으로 JPA 사용시 이와같이 사용하지만 스프링에서 다 자동으로 동작한다.
        try {
            /*
            // 값 입력
            Member member = new Member();
            member.setId(2L);
            member.setName("helloB");

            em.persist(member);

            tx.commit();
            */

            // 값 조회
            Member findMember = em.find(Member.class, 1L);      // 클래스명, PK 값
            System.out.println("findMember id = " + findMember.getId() + "findMember name = " + findMember.getName());

//            em.remove(findMember);    // em.remove() 를 통해 삭제

            // 수정
            findMember.setName("HelloJPA");
            // set 으로만 값 변경되는 이유 : JPA 를 통해서 데이터를 가져오면 JPA 가 관리하며,
            // 데이터의 값이 변경된 것을 트랜잭션 커밋 시 체크하고, 변경되었으면 update 쿼리를 보낸다.


            // JPQL (전체 조회)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)     // 객체를 대상으로 쿼리를 짠다.
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name =" + member.getName());
            }
            
            tx.commit();        // tx.commit() 할 때 commit 나간다.
        } catch (Exception e) {
            // 트랜잭션 롤백
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
