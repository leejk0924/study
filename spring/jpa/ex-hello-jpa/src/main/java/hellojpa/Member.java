package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @Entity 애너테이션은 JPA 를 사용하는 객체라고 인식시켜주고 관리하게 한다.
@Entity
@Table(name="MEMBER")       // 테이블명 매핑
@Setter
@Getter
public class Member {

    @Id     // PK 설정하는 애너테이션이고 반드시 설정해야한다.
    private Long id;
    
    @Column(name="name")    // 필드명 매핑
    private String name;


}
