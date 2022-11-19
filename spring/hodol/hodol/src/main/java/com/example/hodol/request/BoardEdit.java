package com.example.hodol.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
public class BoardEdit {

    @NotBlank(message = "타이틀을 입력하세요.")
    private String title;
    @NotBlank(message = "콘텐츠를 입력해주세요")
    private String content;

    @Builder
    public BoardEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
