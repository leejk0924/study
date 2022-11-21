package com.example.hodol.service;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.BoardEdit;
import com.example.hodol.request.BoardSearch;
import com.example.hodol.request.PostCreate;
import com.example.hodol.response.BoardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {
        // given
        List<Board> requestBoard = IntStream.range(1, 31)
                .mapToObj(i ->
                        Board.builder()
                                .title("title test " + i)
                                .content("content test ")
                                .build())
                .collect(Collectors.toList());

        boardRepository.saveAll(requestBoard);
        BoardSearch boardSearch = BoardSearch.builder()
                .page(1)
                .size(10)
                .build();


        List<BoardResponse> boards = boardService.getList(boardSearch);

        //then
        Assertions.assertEquals(10L, boards.size());
        Assertions.assertEquals("title test 30", boards.get(0).getTitle());
        Assertions.assertEquals("title test 26", boards.get(4).getTitle());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3_1() {
        // given
        List<Board> requestBoard = IntStream.range(0, 20)
                .mapToObj(i ->
                        Board.builder()
                                .title("title test " + i)
                                .content("content test ")
                                .build())
                .collect(Collectors.toList());

        boardRepository.saveAll(requestBoard);
        BoardSearch boardSearch = BoardSearch.builder()
                .page(1)
                .build();
        List<BoardResponse> boards = boardService.getList(boardSearch);
        Assertions.assertEquals(10L, boards.size());
        Assertions.assertEquals("title test 19", boards.get(0).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        // given
        Board board = Board.builder()
                .title("jk")
                .content("jk test")
                .build();

        boardRepository.save(board);
        BoardEdit boardEdit = BoardEdit.builder()
                .title("cr")
                .content("jk test")
                .build();

        //when
        boardService.edit(board.getId(), boardEdit);
        //then
        Board changeBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + board.getId()));

        Assertions.assertEquals("cr", changeBoard.getTitle());
    }

    @Test
    @DisplayName("글 내용 수정")
    void test5() {
        // given
        Board board = Board.builder()
                .title("jk")
                .content("jk test")
                .build();

        boardRepository.save(board);
        BoardEdit boardEdit = BoardEdit.builder()
                .title("jk")
                .content("jk test2")
                .build();

        //when
        boardService.edit(board.getId(), boardEdit);
        //then
        Board changeBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + board.getId()));

        Assertions.assertEquals("jk test2", changeBoard.getContent());
    }
    @Test
    @DisplayName("글 내용 수정")
    void
    test10() {
        // given
        Board board = Board.builder()
                .title("jk")
                .content("jk test")
                .build();

        boardRepository.save(board);
        BoardEdit boardEdit = BoardEdit.builder()
                .content("jk test2")
                .build();

        //when
        boardService.edit(board.getId(), boardEdit);
        //then
        Board changeBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + board.getId()));

        Assertions.assertEquals("jk", changeBoard.getTitle());
        Assertions.assertEquals("jk test2", changeBoard.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        // given
        Board board = Board.builder()
                .title("jk")
                .content("jk test")
                .build();

        boardRepository.save(board);
        boardService.delete(board.getId());
        Assertions.assertEquals(0, boardRepository.count());
    }
}