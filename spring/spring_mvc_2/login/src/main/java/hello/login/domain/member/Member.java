package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id;

    @NotEmpty(message = "ID 값을 입력해 주세요")
    private String loginId; // 로그인 ID
    @NotEmpty(message ="이름을 입력해 주세요")
    private String name;    // 사용자 이름
    @NotEmpty(message = "패스워드를 입력해 주세요")
    private String password;
}
