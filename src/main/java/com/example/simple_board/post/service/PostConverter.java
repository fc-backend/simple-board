package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.reply.service.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostConverter implements Converter<PostRequest, PostDto, PostEntity> {

    private final ReplyConverter replyConverter;
    private final BoardRepository boardRepository;

    @Override
    public PostDto toDtoFromRequest(PostRequest postRequest) {

        return PostDto.builder()
                .boardId(postRequest.getBoardId())
                .userName(postRequest.getUserName())
                .email(postRequest.getEmail())
                .password(postRequest.getPassword())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

    }

    @Override
    public PostDto toDtoFromEntity(PostEntity postEntity) {

        var replyList = postEntity.getReplyList()
                .stream()
                .map(replyConverter::toDtoFromEntity)
                .collect(Collectors.toList());

        return PostDto.builder()
                .id(postEntity.getId())
                .boardId(postEntity.getBoardEntity().getId())
                .userName(postEntity.getUserName())
                .password(postEntity.getPassword())
                .email(postEntity.getEmail())
                .status(postEntity.getStatus())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .replyList(replyList)
                .build();

    }

    @Override
    public PostEntity toEntityFromDto(PostDto postDto) {

        var boardEntity = boardRepository.findById(postDto.getBoardId());

        var replyList = postDto.getReplyList()
                            .stream()
                            .map(replyConverter::toEntityFromDto)
                            .collect(Collectors.toList());

        return PostEntity.builder()
                .id(postDto.getId())
                .boardEntity(boardEntity.orElseGet(() -> null))
                .userName(postDto.getUserName())
                .password(postDto.getPassword())
                .email(postDto.getEmail())
                .status(postDto.getStatus())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postedAt(postDto.getPostedAt())
                .replyList(replyList)
                .build();

    }

    @Override
    public PostEntity updateEntity(PostEntity oldEntity, PostEntity newEntity) {

        oldEntity.setBoardEntity(newEntity.getBoardEntity());
        oldEntity.setUserName(newEntity.getUserName());
        oldEntity.setPassword(newEntity.getPassword());
        oldEntity.setEmail(newEntity.getEmail());
        oldEntity.setStatus(newEntity.getStatus());
        oldEntity.setTitle(newEntity.getTitle());
        oldEntity.setContent(newEntity.getContent());
        oldEntity.setPostedAt(newEntity.getPostedAt());
        oldEntity.setReplyList(newEntity.getReplyList());

        return oldEntity;

    }
}
