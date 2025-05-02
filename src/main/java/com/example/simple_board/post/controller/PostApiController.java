package com.example.simple_board.post.controller;

import com.example.simple_board.crud.CRUDAbstractApiController;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostApiController extends CRUDAbstractApiController<PostRequest, PostDto, PostEntity> {



}
