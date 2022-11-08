package hellojpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Data
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
