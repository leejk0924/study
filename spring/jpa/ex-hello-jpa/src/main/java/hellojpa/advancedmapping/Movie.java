package hellojpa.advancedmapping;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("c")    // DTyp 명 변경
public class Movie extends Item {
    private String director;
    private String actor;
}
