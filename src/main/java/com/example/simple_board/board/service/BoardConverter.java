package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardConverter implements Converter<BoardRequest, BoardDto, BoardEntity> {

    private final PostConverter postConverter;

    @Override
    public BoardDto toDtoFromRequest(BoardRequest boardRequest) {

        return BoardDto.builder()
                                .boardName(boardRequest.getBoardName())
                                .status("REGISTERED")
                                .build();
    }

    @Override
    public BoardDto toDtoFromEntity(BoardEntity boardEntity) {

        var postList = boardEntity.getPostList()
                        .stream()
                        .map(postConverter::toDtoFromEntity)
                        .collect(Collectors.toList());

        return BoardDto.builder()
                            .id(boardEntity.getId())
                            .boardName(boardEntity.getBoardName())
                            .status(boardEntity.getStatus())
                            .postList(postList)
                            .build();

    }

    @Override
    public BoardEntity toEntityFromDto(BoardDto boardDto) {

        var postList = boardDto.getPostList()
                        .stream()
                        .map(postConverter::toEntityFromDto)
                        .collect(Collectors.toList());

        return BoardEntity.builder()
                .id(boardDto.getId())
                .boardName(boardDto.getBoardName())
                .status(boardDto.getStatus())
                .postList(postList)
                .build();

    }

    @Override
    public BoardEntity updateEntity(BoardEntity oldEntity, BoardEntity newEntity) {

        oldEntity.setBoardName(newEntity.getBoardName());
        oldEntity.setStatus(newEntity.getStatus());
        oldEntity.setPostList(newEntity.getPostList());

        return oldEntity;
    }
}
