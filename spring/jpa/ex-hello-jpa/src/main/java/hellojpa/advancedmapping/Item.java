package hellojpa.advancedmapping;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 상속
@DiscriminatorColumn
public abstract class Item{
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private int price;

}
