package com.example.hodol.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardSearch {

    private static final int MAX_SIZE = 2000;

    // Builder.Default 는 Class Level 에서의 @Builder 어노테이션에 동작한다.
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

    public long getOffset() {
        return (long) (Math.max(1, page) - 1) * Math.min(size,MAX_SIZE);
    }
//
//    @Builder
//    public BoardSearch(Integer page, Integer size) {
//        this.page = page;
//        this.size = size;
//    }
}
