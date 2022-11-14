package com.example.hodol.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString()
@NoArgsConstructor
@Setter
@Getter
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요.") // Null 도 체크해준다.
    public String title;
    @NotBlank(message = "콘텐츠를 입력해주세요.")
    public String content;
}
