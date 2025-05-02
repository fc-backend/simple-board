package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.crud.CRUDAbstractService;
import org.springframework.stereotype.Service;

@Service
public class BoardService extends CRUDAbstractService<BoardRequest, BoardDto, BoardEntity> {

}
