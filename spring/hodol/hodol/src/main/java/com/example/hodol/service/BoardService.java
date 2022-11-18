package com.example.hodol.service;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.PostCreate;
import com.example.hodol.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<BoardResponse> getList() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }
}
