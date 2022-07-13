package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// @Entity 애너테이션은 JPA 를 사용하는 객체라고 인식시켜주고 관리하게 한다.
@Entity
@Table(name="MEMBER")       // 테이블명 매핑
@Setter
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    @Column(name="USERNAME")    // 필드명 매핑
    private String userName;

    //    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne
    @JoinColumn(name="TEAM_ID")     // DB와의 관계와 join 하는 컬럼명을 적어주어야 한다.
    private Team team;  // 연관관계의 주인
}
