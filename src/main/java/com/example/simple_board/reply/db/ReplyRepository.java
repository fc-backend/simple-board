package com.example.simple_board.reply.db;

import com.example.simple_board.reply.model.ReplyDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

}
