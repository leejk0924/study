package com.example.hodol.controller;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.BoardEdit;
import com.example.hodol.request.PostCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc       // SpringBootTest 만 붙여주면 MockMvc 를 주입 받을 수 없으므로 해당 어노테이션을 추가
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clean() {
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("/get 요청 시 Hello World 를 출력")
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("get : Hello World"));
    }
    // form 타입
/*    @Test
    @DisplayName("/post 요청 시 Hello World 를 출력")
    void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title","글 제목입니다.")
                        .param("content", "글 내용입니다.")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("post : Hello World"))
                .andDo(MockMvcResultHandlers.print());
    }*/

    // json 타입
    @Test
    @DisplayName("/post 요청 시 Hello World 를 출력")
    void post() throws Exception {
        PostCreate request = PostCreate
                .builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("post : Hello World"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postId").value("1"))
//                .andExpect(MockMvcResultMatchers.content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/post 요청 시 title 값은 필수")
    void postTest2() throws Exception {
        PostCreate request = PostCreate
                .builder()
                .content("test2")
                .build();

        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().string("{}"))  // 해당 검증은 json이 깨져서 나온다.
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test3() throws Exception {
        // given
        PostCreate request = PostCreate
                .builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                                .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
                                // content 를 통해 RequestBody 로 데이터를 보낼 수 있다.
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        assertEquals(1L, boardRepository.count());


        Board board = boardRepository.findAll().get(0);
        assertEquals("제목입니다.", board.getTitle());
        assertEquals("내용입니다.", board.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        // given
        Board board = Board
                .builder()
                .title("123456789012345")
                .content("bar")
                .build();
        boardRepository.save(board);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{postId}", board.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(board.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("bar"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test5() throws Exception {
        // given
        List<Board> requestBoard = IntStream.range(1, 31)
                .mapToObj(i ->
                        Board.builder()
                                .title("title test " + i)
                                .content("content test " + i)
                                .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(requestBoard);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/posts?page=1&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("title test 30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("content test 30"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("페이지를 0으로 요청하면 첫 페이지를 가져온다.")
    void test6() throws Exception {
        // given
        List<Board> requestBoard = IntStream.range(1, 31)
                .mapToObj(i ->
                        Board.builder()
                                .title("title test " + i)
                                .content("content test " + i)
                                .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(requestBoard);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/posts?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("title test 30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("content test 30"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("글제목 수정")
    void test7() throws Exception {
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

        // when
        mockMvc.perform(MockMvcRequestBuilders.patch("/posts/{boardId}", board.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("게시글 삭제제")
    void test8() throws Exception {
        Board board = Board.builder()
                .title("jk")
                .content("jk test")
                .build();
        boardRepository.save(board);

        // expected
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/{postId}", board.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회")
    void test9() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/{boardId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("존재하지 않는 게시글 수정")
    void test10() throws Exception {
        BoardEdit boardEdit = BoardEdit.builder()
                .title("cr")
                .content("jk test")
                .build();

        // when
        mockMvc.perform(MockMvcRequestBuilders.patch("/posts/{boardId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardEdit)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("게시글 작성시 제목에 '바보'는 포함될 수 없다.")
    void test11() throws Exception {
        // given
        PostCreate request = PostCreate
                .builder()
                .title("나는 바보입니다.")
                .content("jk test")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                                .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
                                // content 를 통해 RequestBody 로 데이터를 보낼 수 있다.
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}