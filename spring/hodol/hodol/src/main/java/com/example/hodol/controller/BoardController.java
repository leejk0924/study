package com.example.hodol.controller;

import com.example.hodol.request.BoardSearch;
import com.example.hodol.request.PostCreate;
import com.example.hodol.response.BoardResponse;
import com.example.hodol.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/get")
    public String get() {
        return "get : Hello World";
    }
    /*@PostMapping("/post")
        public String post(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "post : Hello World";
    }*/
/*    @PostMapping("/post")
    public String post(@RequestParam Map<String, String> params) {
        log.info("params={}", params);
        return "post : Hello World";
    }*/
    
    // content-type form 일 경우
/*    @PostMapping("/post")
    public String post(@ModelAttribute PostCreate params) {
        log.info("params={}", params);
        return "post : Hello World";
    }*/
    @PostMapping("/post")
    public Map post(@RequestBody @Valid PostCreate request) throws Exception {
        // Case1. 저장한 데이터 Entity -> response 로 응답하기
        // Case2. 저장한 데이터의 primary_id -> response 로 응답하기
        //         Client 에서는 수신한 id를 글 조회 조회 API 를 통해서 글 데이터를 수신 받음
        // Case3. 응답 필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context 를 잘 관리함
        // Bad Case : 서버에서 -> 반드시 이렇게 할껍니다! fix
        //            -> 서버에서 차라리 유연하게 대응하는게 좋습니다. -> 코드를 잘 짜야함
        //            -> 한 번에 일괄적으로 잘 처리되는 케이스는 없습니다. -> 잘관리하는 형태가 중요
        Long postId = boardService.write(request);
        return Map.of("postId", postId);
        // 잘못된 값을 보내주고 싶지만 controller 로 요청이 오지 않음
    }

    /**
     * /posts -> 글 전체 조회 (검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */
    @GetMapping("/posts/{postId}")
    public BoardResponse get(@PathVariable(name = "postId") Long id) {
        // Request 클래스
        // Response 클래스
        return boardService.get(id);
        // 서비스 정책에 맞는 응답 클래스를 분리하라
    }

    // 조회 API
    // 여러개의 글을 조회하는 API 생성
    @GetMapping("/posts")
    public List<BoardResponse> getList(@ModelAttribute BoardSearch boardSearch) {
        return boardService.getList(boardSearch);
    }
    // 어노테이션 사용해서 페이징 처리
//    @GetMapping("/posts")
//    public List<BoardResponse> getList(@PageableDefault(size = 5) Pageable pageable) {
//        return boardService.getList(pageable);
//    }
}
