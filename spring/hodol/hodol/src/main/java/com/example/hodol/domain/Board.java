package com.example.hodol.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 자바에서는 String 타입이지만 DB 에서는 Long Text 형태로 저장
    // Lob : Long Object 약어
    @Lob
    private String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
