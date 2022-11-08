package hellojpa;

import javax.persistence.*;

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
            Member member = new Member();
            member.setUserName("hello");

            member.setAddress(new Address("city", "street", "10"));
            member.setPeriod(new Period());
            em.persist(member);

        } catch (Exception e) {
            // 트랜잭션 롤백
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
