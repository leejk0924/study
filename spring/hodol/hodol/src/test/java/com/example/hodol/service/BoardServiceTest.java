package com.example.hodol.service;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.PostCreate;
import com.example.hodol.response.BoardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void clean() {
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given

        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        // when
        boardService.write(postCreate);
        // then
        Assertions.assertEquals(1L, boardRepository.count());
        Board board = boardRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", board.getTitle());
        Assertions.assertEquals("내용입니다.", board.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        // given
        Board requestBoard = Board.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        boardRepository.save(requestBoard);

        // when
        BoardResponse board = boardService.get(requestBoard.getId());

        //then
        Assertions.assertNotNull(requestBoard);
        Assertions.assertEquals(1L, boardRepository.count());
        Assertions.assertEquals("제목입니다.", board.getTitle());
        Assertions.assertEquals("내용입니다.", board.getContent());
    }
}