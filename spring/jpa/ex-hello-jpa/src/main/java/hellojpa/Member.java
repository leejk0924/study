package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// @Entity 애너테이션은 JPA 를 사용하는 객체라고 인식시켜주고 관리하게 한다.
@Entity
@Table(name="MEMBER")       // 테이블명 매핑
@Setter
@Getter
public class Member  extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    @Column(name="USERNAME")    // 필드명 매핑
    private String userName;

    // 기간
    @Embedded
    private Period period;

    // 주소
    @Embedded
    private Address address;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address homeAddress;
}
