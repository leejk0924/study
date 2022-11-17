package com.example.hodol.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 서비스 정책에 맞는 클래스
 */

@Getter
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;

    @Builder
    public BoardResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
