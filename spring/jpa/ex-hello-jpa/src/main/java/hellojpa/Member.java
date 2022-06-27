package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// @Entity 애너테이션은 JPA 를 사용하는 객체라고 인식시켜주고 관리하게 한다.
@Entity
@Setter
@Getter
public class Member {

    @Id     // PK 설정하는 애너테이션이고 반드시 설정해야한다.
    private Long id;
    // 필드명 매핑
    @Column(name = "name")
    private String username;
    // 다른타입(가장 적절한 타입으로 자동 변환)
    private Integer age;

    // Enum 타입 사용    (ORDINAL은 사용 X (enum 의 필드값이 순서가 DB 에 저장) -> enum 파일에 필드값이 수정되었을 경우, 예전 데이터가 그대로 남아있음)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 날짜 타입    (자바 8 이후부터는 지원하기 때문에 어노테이션 없이 그냥 해도됨)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 큰 데이터타입을 사용하려며 Lob 사용  (지정할 수 있는 속성이 없음)
    // 필드 타입이 문자 : CLOB 매핑, 나머지 BLOB 매핑
    @Lob
    private String description;

    // DB에 저장하지 않음 (메모리에서만 사용)
    @Transient
    private int temp;
}
