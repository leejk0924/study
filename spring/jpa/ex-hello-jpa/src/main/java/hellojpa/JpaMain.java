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

            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);


            Member member = new Member();
            member.setUserName("member1");
            member.changeTeam(team);
            em.persist(member);

            // 양쪽 모두 값을 넣을땐 값 넣기 가능
            // 해당 코드는 Member의 changeMethod 시 자동으로 실행되게 만든다. (연관관계 편의 메서드)
            //  연관관계 편의 메서드는 어느 곳에 두어도 괜찮지만 한곳에만 만들어야한다.(무한루프 위험 - toString(), lombok, JSON 생성 라이브러리)
//            team.getMembers().add(member);


            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = " + m.getUserName());
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
