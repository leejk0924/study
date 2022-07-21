package hellojpa.advancedmapping;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("A")    // DTyp 명 변경
public class Album  extends Item{
    private String artist;
}
