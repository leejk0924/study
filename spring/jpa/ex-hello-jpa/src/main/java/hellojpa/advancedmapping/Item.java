package hellojpa.advancedmapping;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract  class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;

}
