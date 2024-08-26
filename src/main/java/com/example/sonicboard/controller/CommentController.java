package com.example.sonicboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sonicboard.model.request.CommentPostRequest;
import com.example.sonicboard.model.response.BoardResponse;
import com.example.sonicboard.service.CommentSerivce;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
  private final CommentSerivce commentSerivce;

  //댓글 등록
  @PostMapping("comment")
  public BoardResponse writeComment(
    @RequestBody CommentPostRequest commentPostRequest
  ){
    return  commentSerivce.writeComment(commentPostRequest);
  }



}
