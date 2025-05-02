package com.example.simple_board.reply.service;

import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDto;
import com.example.simple_board.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyRequest, ReplyDto, ReplyEntity> {

    private final PostRepository postRepository;

    @Override
    public ReplyDto toDtoFromRequest(ReplyRequest replyRequest) {

        return  ReplyDto.builder()
                    .postId(replyRequest.getPostId())
                    .userName(replyRequest.getUserName())
                    .password(replyRequest.getPassword())
                    .status("REGISTERED")
                    .title(replyRequest.getTitle())
                    .content(replyRequest.getContent())
                    .repliedAt(LocalDateTime.now())
                    .build();

    }

    @Override
    public ReplyDto toDtoFromEntity(ReplyEntity replyEntity) {

        return ReplyDto.builder()
                    .id(replyEntity.getId())
                    .postId(replyEntity.getPostEntity().getId())
                    .userName(replyEntity.getUserName())
                    .password(replyEntity.getPassword())
                    .status(replyEntity.getStatus())
                    .title(replyEntity.getTitle())
                    .content(replyEntity.getContent())
                    .repliedAt(replyEntity.getRepliedAt())
                    .build();

    }

    @Override
    public ReplyEntity toEntityFromDto(ReplyDto replyDto) {

        var postEntity = postRepository.findById(replyDto.getPostId());

        return ReplyEntity.builder()
                    .id(replyDto.getId())
                    .postEntity(postEntity.orElseGet(() -> null))
                    .userName(replyDto.getUserName())
                    .password(replyDto.getPassword())
                    .status(replyDto.getStatus())
                    .title(replyDto.getTitle())
                    .content(replyDto.getContent())
                    .repliedAt(replyDto.getRepliedAt())
                    .build();

    }

    @Override
    public ReplyEntity updateEntity(ReplyEntity oldEntity, ReplyEntity newEntity) {

        oldEntity.setPostEntity(newEntity.getPostEntity());
        oldEntity.setUserName(newEntity.getUserName());
        oldEntity.setPassword(newEntity.getPassword());
        oldEntity.setStatus(newEntity.getStatus());
        oldEntity.setTitle(newEntity.getTitle());
        oldEntity.setContent(newEntity.getContent());
        oldEntity.setRepliedAt(newEntity.getRepliedAt());

        return oldEntity;
    }
}
