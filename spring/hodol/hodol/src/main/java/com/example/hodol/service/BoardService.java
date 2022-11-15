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


    public void write(PostCreate postCreate) {
        Board board = new Board(postCreate.title, postCreate.content);
        boardRepository.save(board);
    }
}
