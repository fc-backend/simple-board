package com.example.simple_board.reply.service;

import com.example.simple_board.crud.CRUDAbstractService;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDto;
import com.example.simple_board.reply.model.ReplyRequest;
import org.springframework.stereotype.Service;

@Service
public class ReplyService extends CRUDAbstractService<ReplyRequest, ReplyDto, ReplyEntity> {

}
