package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// @Entity 애너테이션은 JPA 를 사용하는 객체라고 인식시켜주고 관리하게 한다.
@Entity
@TableGenerator(
        name="MEMBER_SEQ_GENERATOR",
        table ="MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
@Setter
@Getter
public class Member {

    @Id     // PK 설정하는 애너테이션이고 반드시 설정해야한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 필드명 매핑
    @Column(name = "name", nullable = false)
    private String username;
    // 다른타입(가장 적절한 타입으로 자동 변환)


    public Member() {
    }
}
