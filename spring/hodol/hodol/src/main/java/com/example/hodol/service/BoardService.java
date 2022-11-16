package com.example.hodol.service;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
