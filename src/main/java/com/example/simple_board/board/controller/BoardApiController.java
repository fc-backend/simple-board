package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.crud.CRUDAbstractApiController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardApiController extends CRUDAbstractApiController<BoardRequest, BoardDto, BoardEntity> {

}
