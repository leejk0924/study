package com.example.hodol.service;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.BoardSearch;
import com.example.hodol.request.PostCreate;
import com.example.hodol.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public Long write(PostCreate postCreate) {
        Board board = Board
                .builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        return boardRepository.save(board).getId();
    }

    public BoardResponse get(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        /**
         * 서비스를 2곳으로 나눈다.
         * Controller -> WebBoardService -> Repository
         *               BoardService
         * Response 관련 서비스는 WebBoardService 에서 담당하고, 외부와 연동하는(다른 서비스와 연동) 하는 경우 BoardService 에서 처리하는 경우도 있음
         */
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();

    }
    // 글이 너무 많은 경우 -> 비용이 너무 많이 든다.
    // 글이 -> 100,000,000 -> DB글 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    // DB -> 애플리케이션 서버로 전달하는 시간, 트래픽비용 등이 많이 발생할 수 있다.
    public List<BoardResponse> getList(BoardSearch boardSearch) {
        return boardRepository.getList(boardSearch).stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }
}
