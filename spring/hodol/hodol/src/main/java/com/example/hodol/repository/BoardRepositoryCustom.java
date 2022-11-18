package com.example.hodol.repository;

import com.example.hodol.domain.Board;
import com.example.hodol.request.BoardSearch;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> getList(BoardSearch boardSearch);
}
