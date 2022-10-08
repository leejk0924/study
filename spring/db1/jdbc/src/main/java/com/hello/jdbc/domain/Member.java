package com.hello.jdbc.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// assertThat 하려면 Equals 있어야한다.
@EqualsAndHashCode
public class Member {
    private String memberId;
    private int money;
}
