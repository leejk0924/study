package com.example.hodol.service;

import com.example.hodol.domain.Board;
import com.example.hodol.repository.BoardRepository;
import com.example.hodol.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Board get(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
    }
}
