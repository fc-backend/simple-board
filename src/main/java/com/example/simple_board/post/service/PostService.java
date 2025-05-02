package com.example.simple_board.post.service;

import com.example.simple_board.crud.CRUDAbstractService;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService extends CRUDAbstractService<PostRequest, PostDto, PostEntity> {

}
